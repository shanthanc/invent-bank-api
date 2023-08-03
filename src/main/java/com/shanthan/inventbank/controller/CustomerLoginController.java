package com.shanthan.inventbank.controller;

import com.shanthan.inventbank.exception.InventBankException;
import com.shanthan.inventbank.model.Customer;
import com.shanthan.inventbank.model.CustomerRegisterResponse;
import com.shanthan.inventbank.service.CustomerLoginService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CustomerLoginController {

    private final CustomerLoginService customerLoginService;

    public CustomerLoginController(CustomerLoginService customerLoginService) {
        this.customerLoginService = customerLoginService;
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity<CustomerRegisterResponse> registerCustomer(@Valid @RequestBody Customer customer)
            throws InventBankException {
        Customer registeredCustomer = customerLoginService.registerCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerRegisterResponse.builder()
                        .httpStatus(HttpStatus.CREATED)
                        .customerName(registeredCustomer.getFirstName().concat(" ")
                                .concat(registeredCustomer.getLastName()))
                        .message("Customer Registered successfully")
                .build());
    }

}
