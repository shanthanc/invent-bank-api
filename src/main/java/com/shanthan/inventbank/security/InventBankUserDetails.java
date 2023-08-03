package com.shanthan.inventbank.security;

import com.shanthan.inventbank.repository.CustomerEntity;
import com.shanthan.inventbank.repository.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventBankUserDetails implements UserDetailsService {


    private final CustomerRepository customerRepository;

    public InventBankUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, pass = null;
        List<GrantedAuthority> authorities;

        CustomerEntity customerEntity = customerRepository.findCustomerEntityByUsername(username);
        if (ObjectUtils.isEmpty(customerEntity)) {
            throw new UsernameNotFoundException("No customer with username " + username + " exists. ");
        }
        userName = customerEntity.getUsername();
        pass = customerEntity.getPassword();
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customerEntity.getRole()));

        return new User(userName, pass, authorities);
    }
}
