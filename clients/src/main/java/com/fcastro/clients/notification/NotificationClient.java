package com.fcastro.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification")
public interface NotificationClient {

    @PostMapping("api/V1/notifications")
    public void sendNotification(@RequestBody NotificationRequest notificationRequest);
}
