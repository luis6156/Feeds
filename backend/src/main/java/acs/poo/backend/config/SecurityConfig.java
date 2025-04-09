package acs.poo.backend.config;

import acs.poo.backend.firebase.FirebaseAuthFilter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Optional;

@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final Environment env;
    private final ApplicationContext applicationContext;
    private final FirebaseApp firebaseApp;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
             .cors(AbstractHttpConfigurer::disable)
             .authorizeHttpRequests(auth -> {
                 auth.anyRequest().permitAll();
             });

        try {
            http.addFilterBefore(
                    applicationContext.getBean(FirebaseAuthFilter.class),
                    UsernamePasswordAuthenticationFilter.class
            );
        } catch (BeansException e) {
            log.warn("FirebaseAuthFilter is not enabled. Make sure it is enabled in a production environment.");
        }

        return http.build();
    }

    @Bean(name = "TestToken")
    @ConditionalOnProperty(name = "firebase.auth.test.token-generation", havingValue = "true")
    public Optional<String> testToken() {
        String uid = env.getProperty("firebase.auth.test.uid");
        try {
            String token = FirebaseAuth.getInstance().createCustomToken(uid);
            log.info("Firebase token: {}", token);
            return Optional.of(token);
        } catch (FirebaseAuthException fe) {
            log.warn("FirebaseAuthException occurred while creating test token", fe);
        }

        return Optional.empty();
    }
}
