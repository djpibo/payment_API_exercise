package com.example.taskproject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.taskproject.service.PaymentService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PortoneServiceTest {

    @InjectMocks
    private PaymentService myService;

    @Test
    public void testDoSomething() {
        assertNotNull(myService);

        // 필드 주입이 되었는지 확인
        assertNotNull(myService.getAccessToken());

    }
}
