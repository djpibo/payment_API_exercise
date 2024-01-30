package batch;

import config.CreateJobParameter;
import dto.AccessTokenResponseDTO;
import dto.PortonePayRequestDTO;
import dto.PortonePayResponseDTO;
import entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import repository.MemberRepository;
import repository.SubscriptionOrderRepository;
import repository.SubscriptionRepository;
import repository.UserCardRepository;
import service.PaymentService;
import service.RedisService;
import service.feign.PortoneClient;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SubscriptionBatchConfig {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionOrderRepository subscriptionOrderRepository;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final PaymentService paymentService;
    private final RedisService redisService;
    private final UserCardRepository userCardRepository;
    @JobScope
    private final CreateJobParameter jobParameter;
    private final PortoneClient portoneClient;
    private final MemberRepository memberRepository;

    @Value("${portone.imp.merchant_uid}")
    private String merchantUid;
    public static final String JOB_NAME = "subscriptionBatchJob";

    @Bean(name = JOB_NAME)
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step())
                .build();
    }

    @Bean(name = JOB_NAME +"_step")
    public Step step() {
        return stepBuilderFactory.get(JOB_NAME+"_step")
                .<Subscription, Subscription>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean(name = JOB_NAME +"_reader")
    @StepScope
    public ItemReader<Subscription> reader() {
        Map<String, Object> params = new HashMap<>();
        params.put("createDate", jobParameter.getCreateDate());
        params.put("status", jobParameter.getStatus());

        return new JpaCursorItemReaderBuilder<Subscription>()
                .name(JOB_NAME +"_reader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT * FROM Subscription a WHERE a.paidDate =:paidDate AND a.status =:status ")
                .parameterValues(params)
                .build();
    }
    @Bean(name = JOB_NAME +"_processor")
    @StepScope
    public ItemProcessor<Subscription, Subscription> processor() {
        return list -> {
            UserCard userCard = userCardRepository.findUserCardById(list.getId());
            PortonePayRequestDTO portonePayRequestDTO = PortonePayRequestDTO.builder()
                    .cardNumber(userCard.getCardNumber())
                    .expiry((userCard.getExpiry()))
                    .amount(list.getPayAmount())
                    .birth(jobParameter.getUserId())
                    .merchantUid(merchantUid)
                    .build();
            PortonePayResponseDTO portonePayResponseDTO
                    = portoneClient.postSubscribePaymentOnetime("Bearer " + redisService.getDataFromRedis("Access-Token"), portonePayRequestDTO);

            if (portonePayResponseDTO.getCode() == HttpStatus.UNAUTHORIZED.value()) {
                AccessTokenResponseDTO accessTokenResponseDTO = paymentService.getAccessToken();
                redisService.saveDataToRedis("Access-Token", accessTokenResponseDTO.getResponse().getAccessToken());
                portonePayResponseDTO
                        = portoneClient.postSubscribePaymentOnetime("Bearer " + redisService.getDataFromRedis("Access-Token"), portonePayRequestDTO);
            }
            return list;
        };
    }

    private ItemWriter<Subscription> writer() {
        return list -> {
            for (Subscription subscription: list) {
                subscriptionRepository.plusTotalPaidCount(subscription.getId());
                SubscriptionOrder subscriptionOrder = SubscriptionOrder.builder()
                        .paidSuccessYn(true)
                        .lastPaidDate(LocalDateTime.now())
                        .build();
                subscriptionOrderRepository.save(subscriptionOrder);
            }
        };
    }
}
