package co.com.nisum.model.token.gateways;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface TokenRepository {

    Mono<String> createToken();
}
