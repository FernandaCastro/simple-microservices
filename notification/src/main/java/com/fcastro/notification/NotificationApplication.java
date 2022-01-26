package com.fcastro.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.fcastro.notification",
                "com.fcastro.amqp",
        }
)
@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer producer,
//            NotificationConfig notificationConfig
//    ) {
//        return args -> {
//            //ConnectionFactory connectionFactory = new CachingConnectionFactory();
//            //AmqpAdmin admin = new RabbitAdmin(connectionFactory);
//            //admin.declareQueue(new Queue("myqueue"));
//            //AmqpTemplate template = new RabbitTemplate(connectionFactory);
//            //template.convertAndSend("notification.queue", "foo");
//            //String foo = (String) template.receiveAndConvert("myqueue");
//            //System.out.println(foo);
//
//
//            producer.publish(
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey(),
//                    new Person("Ali", 20));
//        };
//    }
//
//    record Person(String name, int age) {
//    }

}
