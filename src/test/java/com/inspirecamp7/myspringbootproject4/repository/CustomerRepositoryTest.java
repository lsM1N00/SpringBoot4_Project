package com.inspirecamp7.myspringbootproject4.repository;

import com.inspirecamp7.myspringbootproject4.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testInsert(){
        //Given-When-Then 테스트할 때는 이 3단계로 이루어지다.
        //Given(준비단계)
        Customer customer = new Customer();
        customer.setCustomerId("B001");
        customer.setCustomerName("스프링B");

        //When(실행단계)
        Customer addedCustomer = customerRepository.save(customer);

        //Then(검증단계)
        assertThat(addedCustomer).isNotNull();
        assertThat(addedCustomer.getCustomerId()).isEqualTo("B001");
        assertThat(addedCustomer.getCustomerName()).isEqualTo("스프링B");

    }

    //2.Customer 조회
    @Test
    void testRead() {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId("B001");
    }

}