package co.com.nisum.model.user.gateways;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserResponse;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UserRepository {

    Mono<UserResponse> save(User user);
}
