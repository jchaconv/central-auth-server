package com.jchacon.authserver.loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.UUID;

@Configuration
public class ClientLoader {

    @Bean
    public CommandLineRunner initClients(RegisteredClientRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if our service already exists to avoid duplicates
            if (repository.findByClientId("service-one") == null) {

                RegisteredClient serviceOne = RegisteredClient.withId(UUID.randomUUID().toString())
                        .clientId("service-one")
                        // Standard practice: encode secret before saving to DB
                        .clientSecret(passwordEncoder.encode("secret1"))
                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .scope("read:data")
                        .scope("write:data")
                        .tokenSettings(TokenSettings.builder()
                                .accessTokenTimeToLive(Duration.ofHours(1))
                                .build())
                        .build();

                repository.save(serviceOne);
                System.out.println(">>> Seed Data: service-one registered successfully.");
            }
        };
    }
}