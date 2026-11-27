package com.inspirecamp7.myspringbootproject4.repository;

import com.inspirecamp7.myspringbootproject4.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    //1. Customer 등록
    @Test
    @Transactional
    //등록된 데이트를 반드시 확인하고 싶으니 RollBack 처리를 하지 마세요. 자동으로 Rollback이 true로 설정되어있음
    @Rollback(value = false)
    void testInsert(){
        //Given-When-Then 테스트할 때는 이 3단계로 이루어지다.
        //Given(준비단계)
        Customer customer = new Customer();
        customer.setCustomerId("AB001");
        customer.setCustomerName("스프링AB");

        //When(실행단계)
        Customer addedCustomer = customerRepository.save(customer);

        //Then(검증단계)
        assertThat(addedCustomer).isNotNull();
        assertThat(addedCustomer.getCustomerId()).isEqualTo("AB001");
        assertThat(addedCustomer.getCustomerName()).isEqualTo("스프링AB");

    }

    //1.1 Customer 수정
    @Test
    void testUpdate() {
       Customer customer = customerRepository.findByCustomerId(("A001"))
                .orElseGet(() -> new Customer());
       customer.setCustomerName("SpringA");
       customerRepository.save(customer);
    }

    //2.Customer 조회
    @Test
    @Transactional(readOnly = true)
    void testRead() {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId("B001");
        //Optional이 담고 있는 Customer가 값ㅇ르 가지고 있으면?
        if(optionalCustomer.isPresent()){
            Customer existCustomer = optionalCustomer.get();
            assertThat(existCustomer.getCustomerId()).isEqualTo("B001");
        }

        //함수형 인터페이스 Customer의 추상메서드 void accept(T t)
        optionalCustomer.ifPresent(customer -> System.out.print(customer.getCustomerName()));
    }


    //3. Customer  조회( 값이 일치하는 Customer 존재하지 않는 경우와 존재 하는 경우
    @Test
    void testReadNotFound() {
        //Optional orElseGet(Supplier supplier)
        //함수형 인터페이스 Supplier 추상메서드 T get()
        Customer existCustomer = customerRepository.findByCustomerId("B001")
                .orElseGet(() -> new Customer());

        //Customer가 값이 있는 경우 성공함
        assertThat(existCustomer.getCustomerId()).isNotNull();

        Customer notExistCustomer = customerRepository.findByCustomerId("F001")
                .orElseGet(() -> new Customer());
        //Customer가 값이 없는 경우 성공함
        assertThat(notExistCustomer.getCustomerId()).isNull();
    }

    @Test
    void testReadNotFoundException() {
        //Optional orElseThrow(Supplier <? extends x> supplier)
        // <x extends Throwable>
        //함수형 인터페이스 Supplier 추상메서드 T get()
        Customer existCustomer = customerRepository.findByCustomerId("B001")
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        assertThat(existCustomer.getCustomerId()).isNotNull();

        Customer notExistCustomer = customerRepository.findByCustomerId("F001")
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        assertThat(notExistCustomer.getCustomerId()).isNull();
    }

}