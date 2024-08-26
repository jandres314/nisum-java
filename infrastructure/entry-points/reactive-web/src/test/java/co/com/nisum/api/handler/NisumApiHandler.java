package co.com.nisum.api.handler;

import co.com.nisum.api.mapper.MapperRequest;
import co.com.nisum.api.mapper.MapperResponse;
import co.com.nisum.api.model.UserViewRequest;
import co.com.nisum.api.model.UserViewResponse;
import co.com.nisum.model.user.UserResponse;
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

    public Mono<UserViewResponse> save(UserViewRequest userRequest) {

        return null;
    }
}
