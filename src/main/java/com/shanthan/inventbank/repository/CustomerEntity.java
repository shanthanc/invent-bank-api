package com.shanthan.inventbank.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long customerId;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Column(unique = true)
    private String username;

    @NotEmpty
    @Column(unique = true)
    private String password;

    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String role;
}
