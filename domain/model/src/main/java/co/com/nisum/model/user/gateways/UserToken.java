package co.com.nisum.model.user.gateways;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UserToken {

    Mono<String> createToken();
}
