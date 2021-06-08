package com.spring.practice.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FCMConfig {
    private FirebaseApp instance = null;

    @Bean
    public FirebaseApp getFirebaseApp() throws Exception {
        if (instance == null && !FirebaseApp.getApps().isEmpty()) {
            for (FirebaseApp app : FirebaseApp.getApps()) {
                if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                    instance = app;
                    break;
                }
            }
        } else {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("fcm-spring.json").getInputStream()))
                    .build();

            instance = FirebaseApp.initializeApp(options);
        }

        return instance;
    }
}
