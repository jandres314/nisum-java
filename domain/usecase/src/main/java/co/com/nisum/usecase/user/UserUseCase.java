package co.com.nisum.usecase.user;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserResponse;
import co.com.nisum.model.user.exceptions.BusinessException;
import co.com.nisum.model.user.gateways.UserRepository;
import co.com.nisum.model.user.gateways.UserToken;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    private final UserToken userToken;

    public Mono<UserResponse> save(User user) {
        return userRepository.findUserIdByEmail(user.getEmail())
                .hasElement().switchIfEmpty(Mono.just(Boolean.FALSE))
                .doOnNext(hasEmail -> validateEmptyUserId(hasEmail, user))
                .flatMap(hasEmail -> userToken.createToken())
                .flatMap(token -> userRepository.save(user, token));
    }

    private void validateEmptyUserId(boolean hasEmail, User user) {
        if (hasEmail) {
            var msg = "El correo %s ya se encuentra registrado";
            throw new BusinessException(String.format(msg, user.getEmail()));
        }
    }

}
