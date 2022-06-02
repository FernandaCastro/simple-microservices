#Simple-Microservices 

The purpose of this project is to learn how microservices communicate to each other and how they can be structured using some of the current technologies.

The project evolved gradually, from the simplest to a more sophisticated solution, by refactoring to deal with:
- ***Spring RestTemplate*** - Synchronous HTTP requests on the client side
- ***Netflix Eureka*** - Service Registration and Discovery
- ***Open Feign*** - Java to HTTP client binder
- ***Spring Cloud Sleuth*** - Distributed Trace
- ***Zipkin*** - Trace Visualization
- ***Spring Cloud Gateway*** - For building API Gateway with cross-cutting concerns
- ***RabbitMQ*** - Distributed Message Broker
- ***Kafka*** - Distributed Event Streaming Platform
- ***Spring Profiles*** and packaging using ***Jib***
- ***Docker***
- ***Kubernetes*** - (locally with ***Mini-Kube***)

This was all based on @amigoscode Microservice training.

![img.png](amigoscode-microservices-diagram.png)