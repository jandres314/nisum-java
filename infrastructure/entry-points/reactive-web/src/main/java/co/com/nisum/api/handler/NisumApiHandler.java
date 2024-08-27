package co.com.nisum.api.handler;

import co.com.nisum.api.consumer.PasswordConsumer;
import co.com.nisum.api.mapper.MapperRequest;
import co.com.nisum.api.mapper.MapperResponse;
import co.com.nisum.api.model.UserRequestView;
import co.com.nisum.api.model.UserResponseView;
import co.com.nisum.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class NisumApiHandler {

    private final UserUseCase userUseCase;
    private final MapperRequest mapperRequest;
    private final MapperResponse mapperResponse;
    private final PasswordConsumer passwordConsumer;

    public Mono<UserResponseView> save(UserRequestView userRequest) {
        passwordConsumer.accept(userRequest.getPassword());
        return userUseCase.save(mapperRequest.apply(userRequest))
                .map(mapperResponse);
    }

}
