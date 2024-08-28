package co.com.nisum.config;

import co.com.nisum.model.user.gateways.UserRepository;
import co.com.nisum.model.user.gateways.UserToken;
import co.com.nisum.usecase.user.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public UserUseCase userUseCase(UserRepository userRepository, UserToken userToken) {
        return new UserUseCase(userRepository, userToken);
    }
}
