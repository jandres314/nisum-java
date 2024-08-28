package co.com.nisum.usecase.token;

import co.com.nisum.model.token.gateways.TokenRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TokenUseCase {

    private final TokenRepository tokenRepository;

    public Mono<String> createToken() {
        return tokenRepository.createToken();
    }

}
