package com.fcastro.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //TODO: Check if email is valid
        //TODO: Check if email not taken
        //TODO: Store Customer in DB
        customerRepository.save(customer);
    }
}
