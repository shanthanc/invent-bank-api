package com.shanthan.inventbank.controller;

import com.shanthan.inventbank.exception.InventBankException;
import com.shanthan.inventbank.model.Customer;
import com.shanthan.inventbank.model.SuccessResponse;
import com.shanthan.inventbank.service.InventBankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventBankController {

    private final InventBankService inventBankService;

    public InventBankController(InventBankService inventBankService) {
        this.inventBankService = inventBankService;
    }

    @GetMapping("/myAccount")
    public String getAccountDetails() {
        return "Account Details from Db ";
    }

    @GetMapping("/loanDetails")
    public String getLoanDetails() {
        return "Loan details from Db";
    }

    @GetMapping("/myCardDetails")
    public String getMyCardDetails() {

        return "My card details from Db";
    }

    @GetMapping("/contactDetails")
    public String getContactDetails() {
        return "Contact Details ";
    }

    @GetMapping("/getNotices")
    public String getNotices() {
        return "Notices from Db ";
    }

    @GetMapping("/getAllCustomerDetails")
    public ResponseEntity getAllCustomerDetails() throws InventBankException {
        List<Customer> responseBody = inventBankService.getAllCustomersFromDb();
        if (responseBody.isEmpty()) {
            return ResponseEntity.ok(SuccessResponse.builder()
                            .httpStatus(HttpStatus.OK)
                            .message("No customers registered. ")
                    .build());
        }
        else {
            return ResponseEntity.ok(responseBody);
        }

    }
}
