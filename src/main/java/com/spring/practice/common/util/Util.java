package com.spring.practice.common.util;

import com.google.firebase.messaging.*;

public class Util {
    public Message createMessage(String token) {
        Notification notification = Notification.builder()
                .setTitle("스프링 테스트 메세지")
                .setBody("안녕하세요! 스프링과 FCM 연동 연습용 메세지입니다.")
                .build();

        return Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(600 * 1000)
                        .setNotification(AndroidNotification.builder()
                                .setPriority(AndroidNotification.Priority.HIGH)
                                .build())
                        .build())
                .setApnsConfig(ApnsConfig.builder()
                        .setAps(Aps.builder()
                                .setSound("default")
                                .setContentAvailable(true)
                                .build())
                        .build())
                .setNotification(notification)
                .setToken(token)
                .putData("link", "https://www.naver.com")
                .build();
    }
}