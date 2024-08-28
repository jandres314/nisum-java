package co.com.nisum.config;

import co.com.nisum.jwtgeneration.TokenService;
import co.com.nisum.model.token.Token;
import co.com.nisum.model.token.gateways.TokenRepository;
import co.com.nisum.model.user.gateways.UserRepository;
import co.com.nisum.usecase.token.TokenUseCase;
import co.com.nisum.usecase.user.UserUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public UserUseCase userUseCase(UserRepository userRepository, TokenUseCase tokenUseCase) {
        return new UserUseCase(userRepository, tokenUseCase);
    }

    @Bean
    public TokenUseCase tokenUseCase(TokenRepository tokenRepository) {
        return new TokenUseCase(tokenRepository);
    }

    @Bean
    public TokenRepository tokenRepository(@Value("${app.token.secretKey}")
                                           String secretKeyToken,
                                           @Value("${app.token.keyId}")
                                           String keyIdToken,
                                           @Value("${app.token.subject}")
                                           String subjectToken,
                                           @Value("${app.token.issuer}")
                                           String issuerToken,
                                           @Value("${app.token.secondsToExpiration}")
                                           Integer secondsToExpiration) {
        var tokenData = Token.builder()
                .secretKey(secretKeyToken)
                .keyId(keyIdToken)
                .subject(subjectToken)
                .issuer(issuerToken)
                .secondsToExpiration(secondsToExpiration)
                .build();
        return new TokenService(tokenData);
    }

}
