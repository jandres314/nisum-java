package co.com.nisum.config.app;

import co.com.nisum.api.consumer.PasswordConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordConsumerConfig {

    @Bean
    public PasswordConsumer passwordConsumer(@Value("${app.passwordRegex}") String passwordRegex) {
        return new PasswordConsumer(passwordRegex);
    }

}
