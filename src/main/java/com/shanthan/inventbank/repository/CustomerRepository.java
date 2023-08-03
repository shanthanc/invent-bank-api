package com.shanthan.inventbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findCustomerEntityByCustomerId(Long customerId);

    CustomerEntity findCustomerEntityByUsername(String username);

    CustomerEntity findCustomerEntityByEmail(String email);

    //List<CustomerEntity> get

}
