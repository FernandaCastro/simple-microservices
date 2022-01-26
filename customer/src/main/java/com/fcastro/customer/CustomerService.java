package com.fcastro.customer;

import com.fcastro.amqp.RabbitMQMessageProducer;
import com.fcastro.clients.fraud.FraudCheckResponse;
import com.fcastro.clients.fraud.FraudClient;
import com.fcastro.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //TODO: Check if email is valid
        //TODO: Check if email not taken
        customerRepository.saveAndFlush(customer);

        //3 - Open Feign + Eureka Clients
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()){
                throw new IllegalStateException("Fraudster: " + customer.getId());
        }

        //Notify someone
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome",
                        customer.getFirstName()));

        rabbitMQMessageProducer.publish(
                "internal.exchange",
                "internal.notification.routing-key",
                notificationRequest);
    }
}
