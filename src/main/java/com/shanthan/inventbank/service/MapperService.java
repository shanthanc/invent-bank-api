package com.shanthan.inventbank.service;

import com.shanthan.inventbank.exception.InventBankException;
import com.shanthan.inventbank.model.Customer;
import com.shanthan.inventbank.repository.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Slf4j
public class MapperService {

    public Customer entityToObject(CustomerEntity customerEntity) throws InventBankException {
        Customer customer;
        try {
            log.info("Mapping customer entity to object ");
            customer = Customer.builder()
                    .firstName(customerEntity.getFirstName())
                    .lastName(customerEntity.getLastName())
                    .username(customerEntity.getUsername())
                    .password(customerEntity.getPassword())
                    .email(customerEntity.getEmail())
                    .role(customerEntity.getRole())
                    .build();
        } catch (Exception ex) {
            log.error("Error while mapping customer entity to object ");
            throw new InventBankException(INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return customer;
    }

    public CustomerEntity objectToEntity(Customer customer) throws InventBankException {

        CustomerEntity customerEntity;
        try {
            log.info("Mapping customer object to entity ");
            customerEntity = CustomerEntity.builder()
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .username(customer.getUsername())
                    .password(customer.getPassword())
                    .email(customer.getEmail())
                    .role(customer.getRole())
                    .build();
        } catch (Exception ex) {
            log.error("Error while mapping customer object to entity ");
            throw new InventBankException(INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return customerEntity;
    }
}
