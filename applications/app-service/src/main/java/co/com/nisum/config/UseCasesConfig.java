package co.com.nisum.config;

import co.com.nisum.model.user.gateways.UserRepository;
import co.com.nisum.usecase.user.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configuration
public class UseCasesConfig {

    @Bean
    public UserUseCase userUseCase(UserRepository userRepository) {
        return new UserUseCase(userRepository, () -> Mono.just(UUID.randomUUID().toString()));
    }
}
