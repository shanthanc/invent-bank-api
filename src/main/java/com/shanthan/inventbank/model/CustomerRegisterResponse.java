package com.shanthan.inventbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegisterResponse {

    private HttpStatus httpStatus;

    private String customerName;

    private String message;
}
