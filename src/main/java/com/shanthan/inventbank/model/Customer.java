package com.shanthan.inventbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @JsonIgnore
    private Long customerId;
    @NotEmpty
    @Size(min = 1, max = 50)
    private String firstName;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String lastName;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String username;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String password;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String email;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String role;
}
