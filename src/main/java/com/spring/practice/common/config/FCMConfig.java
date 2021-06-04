package com.spring.practice.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;

@Configuration
public class FCMConfig {
    private FirebaseApp instance = null;

    @Bean
    @SuppressWarnings("deprecation")
    public FirebaseApp getFirebaseApp() throws Exception {
        if (instance == null && !FirebaseApp.getApps().isEmpty()) {
            for (FirebaseApp app : FirebaseApp.getApps()) {
                if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                    instance = app;
                    break;
                }
            }
        } else {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/fcm-spring.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            instance = FirebaseApp.initializeApp(options);
        }

        return instance;
    }
}
