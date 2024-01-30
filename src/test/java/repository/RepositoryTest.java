package repository;

import entity.Member;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration
public class RepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveMember() {
        Member member = memberRepository.findUserById(1);
        assertThat(member).isNotNull();
    }
}