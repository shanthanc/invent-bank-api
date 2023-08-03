package com.shanthan.inventbank.security;

import com.shanthan.inventbank.repository.CustomerEntity;
import com.shanthan.inventbank.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@Slf4j
public class InventBankAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    public InventBankAuthenticationProvider(CustomerRepository customerRepository,
                                            PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomerEntity customerEntity = customerRepository.findCustomerEntityByUsername(username);
        if (isEmpty(customerEntity)) {
            throw new BadCredentialsException("User not registered. ");
        }

        String encodedPassword = customerEntity.getPassword();
        if (passwordEncoder.matches(password, encodedPassword)) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customerEntity.getRole()));
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }
        else {
            throw new BadCredentialsException("Username and/or password not valid. Please try again. ");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
