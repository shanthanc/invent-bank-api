package com.shanthan.inventbank.service;

import com.shanthan.inventbank.exception.InventBankException;
import com.shanthan.inventbank.model.Customer;
import com.shanthan.inventbank.repository.CustomerEntity;
import com.shanthan.inventbank.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.*;

@Service
@Slf4j
public class CustomerLoginService {

    private final CustomerRepository customerRepository;
    private final MapperService mapperService;

    private final PasswordEncoder passwordEncoder;

    public CustomerLoginService(CustomerRepository customerRepository, MapperService mapperService,
                                PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.mapperService = mapperService;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer registerCustomer(Customer customer) throws InventBankException {

        Customer registeredCustomer;
        try {
            log.info("Securing customer sensitive information ");
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));

            log.info("Sensitive info secured. Registering new customer with name {} {} ", customer.getFirstName(),
                    customer.getLastName());
            CustomerEntity customerEntity = mapperService.objectToEntity(customer);

            CustomerEntity registeredCustomerEntity = customerRepository.saveAndFlush(customerEntity);
            registeredCustomer = mapperService.entityToObject(registeredCustomerEntity);

        } catch (InventBankException ibe) {
            log.error("Error while registering customer ");
            throw new InventBankException(ibe.getHttpStatus(), ibe.getMessage());

        } catch (Exception ex) {
            log.error("Error while registering customer ");
            throw new InventBankException(INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        log.info("Customer with name {} {} registered ", customer.getFirstName(), customer.getLastName());
        return registeredCustomer;
    }
}
