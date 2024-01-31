package com.example.taskproject.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignInterceptor implements RequestInterceptor {

    private final String token;

    public FeignInterceptor(String token) {
        this.token = token;
    }

    @Override
    public void apply(RequestTemplate template) {
        // 특정 메소드에만 헤더를 추가하도록 로직을 작성
        if (template.url().contains("/subscribe/payments")) {
            template.header("Authorization", "Bearer " + token);
        }
    }
}
