package com.inspirecamp7.myspringbootproject4.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Consumer;

@Component
@Order(2)
public class MyRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    String myBootName;

    @Value("${myboot.age}")
    int mybootAge;

    @Autowired
    Environment enviroment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("환경변수 myboot.name = " + myBootName);
        System.out.println("환경변수 myboot.age = " + mybootAge);
        System.out.println("환경변수 myboot.fullName = " + enviroment.getProperty("myboot.fullName"));

        System.out.println("server.port 환경변수 확인 " + args.containsOption("server.port"));
        System.out.println("myboot.name 환경변수 확인 " + args.containsOption("myboot.name"));
        System.out.println("server.port Program argument 확인 " + args.containsOption("server.port"));
        System.out.println("myboot.name Program argument 확인 " + args.containsOption("myboot.name"));

        //환경변수 목록을 확인하기
        Set<String> optionNames = args.getOptionNames();
        //기존의 for loop
        //0번
        System.out.println("1번");
        for(String name: optionNames){
            System.out.println("name = " + name);
        }
        //1번 Anonymous Inner Class (익명 클래스 내부)
        System.out.println("1단계");
        optionNames.forEach(new Consumer<String>() {
            @Override
            public void accept(String name) {
                System.out.println("Inner Class인 경우 name = " + name);
            }
        });

        //Iterable 의 default void forEach(Consumer<? super T> action)
        //함수형 인터페이스의 추상 메서드 void accept(T t)
        //Comsumer 인터페이스의 accept() 메서드를 람다식으로 표현
        //arg -> body
        //2번 - 람다 표현식
        System.out.println("2단계");
        optionNames.forEach(optionName -> System.out.println(optionName));

        //Comsumer 인터페이스의 accept() 메서드를 Method Reference로 표햔하기
        //3번 - Method Reference (body만)
        System.out.println("3단계");
        optionNames.forEach(System.out::println);



    }
}