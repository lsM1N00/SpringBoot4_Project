package com.inspirecamp7.myspringbootproject4.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2) // 먼저 실행하는 순서 결정
public class MyRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
