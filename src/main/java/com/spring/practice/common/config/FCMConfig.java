package com.spring.practice.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.FileInputStream;

@Configuration
public class FCMConfig {
    @Bean
    public FirebaseApp getFirebaseApp() throws Exception {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/spring-practice-8e691-firebase-adminsdk-5w3w7-b8a0264afa.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
