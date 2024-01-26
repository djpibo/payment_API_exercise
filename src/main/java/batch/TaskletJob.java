package batch;

import jakarta.transaction.TransactionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class TaskletJob {
/*
    private final JobBuilder jobBuilder;
    private final StepBuilder stepBuilder;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job myFirstJob(JobRepository jobRepository){
        return new JobBuilder("myFirstJob", jobRepository)
                .start(myFirstStep(jobRepository))
                .on("FAILED")
                .to(failStep())
                .on("*")
                .end()
                .build();
    }

    @Bean
    public Step myFirstStep(JobRepository jobRepository){
        return new StepBuilder("myFirstStep",jobRepository)
                .<String, String>chunk(1000,transactionManager)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Step failStep(){
        return new StringBuilder("failStep")
                .<String, String>chunk(1000,transactionManager)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }
    @Bean
    public ItemReader<String> itemReader(){
        return new ItemReader<String>() {
            @Override
            public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                return "Read OK";
            }
        };
    }

    @Bean
    public ItemWriter<String> itemWriter(){
        return strList -> {
            strList.forEach(
                    str -> log.info("str: {}", str)
            );
        };
    }
*/
}
