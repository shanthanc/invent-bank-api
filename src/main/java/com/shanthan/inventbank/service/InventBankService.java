package com.shanthan.inventbank.service;

import com.shanthan.inventbank.exception.InventBankException;
import com.shanthan.inventbank.model.Customer;
import com.shanthan.inventbank.repository.CustomerEntity;
import com.shanthan.inventbank.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
@Slf4j
public class InventBankService {

    private final CustomerRepository customerRepository;

    private final MapperService mapperService;

    public InventBankService(CustomerRepository customerRepository, MapperService mapperService) {
        this.customerRepository = customerRepository;
        this.mapperService = mapperService;
    }

    public List<Customer> getAllCustomersFromDb() throws InventBankException {
        List<Customer> customers = new ArrayList<>();

        try {
            List<CustomerEntity> customerEntities = customerRepository.findAll();
            for (CustomerEntity customerEntity : customerEntities) {
                Customer customer = mapperService.entityToObject(customerEntity);
                customers.add(customer);
            }
        } catch (Exception ex) {
            log.error("Error retrieving list of all customers registered. ");
            throw new InventBankException(INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return customers;
    }
}
