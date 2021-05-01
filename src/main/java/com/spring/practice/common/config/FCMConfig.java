package com.spring.practice.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;

@Configuration
public class FCMConfig {
    @Bean
    @SuppressWarnings("deprecation")
    public FirebaseApp getFirebaseApp() throws Exception {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/fcm-spring.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
