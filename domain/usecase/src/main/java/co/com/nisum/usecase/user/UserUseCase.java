package co.com.nisum.usecase.user;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserResponse;
import co.com.nisum.model.user.exceptions.BusinessException;
import co.com.nisum.model.user.gateways.UserRepository;
import co.com.nisum.model.user.gateways.UserToken;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    private final UserToken userToken;

    public Mono<UserResponse> save(User user) {
        return userRepository.findUserIdByEmail(user.getEmail())
                .doOnNext(userId -> this.validateEmptyUserId(userId, user))
                .flatMap(userId -> userToken.createToken())
                .flatMap(token -> userRepository.save(user, token));
    }

    private void validateEmptyUserId(UUID userId, User user) {
        if(Objects.nonNull(userId)) {
            var msg = "El correo %s ya se encuentra registrado";
            throw new BusinessException(String.format(msg, user.getEmail()));
        }
    }

}
