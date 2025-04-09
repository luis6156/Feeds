package acs.poo.backend.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.Objects;

@Log4j2
@Configuration
public class FirebaseConfig {
    private static final String CONFIG_PATH = "firebase.json";

    @Bean
    public FirebaseApp initFirebase() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(CONFIG_PATH)) {
            FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(Objects.requireNonNull(is)))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                return FirebaseApp.initializeApp(firebaseOptions);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return FirebaseApp.getInstance();
    }
}
