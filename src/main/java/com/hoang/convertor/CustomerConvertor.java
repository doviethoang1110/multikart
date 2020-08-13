package com.hoang.convertor;

import com.hoang.entities.Customer;
import com.hoang.payload.request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerConvertor {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Customer convertor(CustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setEnabled(false);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        return customer;
    }
}
