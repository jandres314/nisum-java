package co.com.nisum.api.handler;

import static org.mockito.Mockito.*;
import co.com.nisum.api.consumer.PasswordConsumer;
import co.com.nisum.api.mapper.MapperRequest;
import co.com.nisum.api.mapper.MapperResponse;
import co.com.nisum.api.model.UserRequestView;
import co.com.nisum.api.model.UserResponseView;
import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserResponse;
import co.com.nisum.usecase.user.UserUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class NisumApiHandlerTest {

    @InjectMocks
    private NisumApiHandler nisumApiHandler;

    @Mock
    private UserUseCase userUseCase;

    @Mock
    private MapperRequest mapperRequest;

    @Mock
    private MapperResponse mapperResponse;

    @Mock
    private PasswordConsumer passwordConsumer;

    @BeforeEach
    void setUp() {
        doNothing().when(passwordConsumer).accept(anyString());
        when(userUseCase.save(any(User.class)))
                .thenReturn(Mono.just(new UserResponse()));
        when(mapperRequest.apply(any(UserRequestView.class)))
                .thenReturn(new User());
        when(mapperResponse.apply(any(UserResponse.class)))
                .thenReturn(new UserResponseView());
    }

    @AfterEach
    void tearDown() {
        verify(passwordConsumer).accept(anyString());
        verify(userUseCase).save(any(User.class));
        verify(mapperRequest).apply(any(UserRequestView.class));
        verify(mapperResponse).apply(any(UserResponse.class));
    }

    @Test
    void save() {
        var userView = UserRequestView.builder().password("changeme").build();
        var mono = nisumApiHandler.save(userView);
        StepVerifier.create(mono).assertNext(Assertions::assertNotNull).verifyComplete();
    }

}
