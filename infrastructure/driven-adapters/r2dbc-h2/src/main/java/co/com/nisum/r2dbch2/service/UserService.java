package co.com.nisum.r2dbch2.service;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserResponse;
import co.com.nisum.model.user.gateways.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserService implements UserRepository {
    @Override
    public Mono<UserResponse> save(User user, String token) {
        return Mono.just(new UserResponse());
    }

    @Override
    public Mono<UUID> findUserIdByEmail(String email) {
        return Mono.empty();
    }
}
