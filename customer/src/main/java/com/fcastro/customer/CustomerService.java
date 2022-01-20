package com.fcastro.customer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //TODO: Check if email is valid
        //TODO: Check if email not taken
        customerRepository.saveAndFlush(customer);

        //Check if fraudster

        //1 - Basic alternative to communicate to other services: inform HOST and PORT
        /*      FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/V1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());
        */

        //2 - Service Discover (EureKa Server) an important and fragile bottleneck: Finds out the HOST and PORT
        //      a. Customer and Fraud instances register as clients,
        //      b. Customer sends a service discovery requesto to Eurela Server
        //      c. Customer asks for Fraud instance location
        //      d. Customer send requests to Fraud
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/V1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        if (fraudCheckResponse != null){
            if(fraudCheckResponse.isFraudster()){
                throw new IllegalStateException("Fraudster: " + customer.getId());
            }
        }
    }
}
