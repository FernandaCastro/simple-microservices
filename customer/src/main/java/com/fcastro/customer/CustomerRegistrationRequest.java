package com.fcastro.customer;

//Using records to get for free the immutability for Strings and etc.
public record CustomerRegistrationRequest(
    String firstName,
    String lastName,
    String email) {
}
