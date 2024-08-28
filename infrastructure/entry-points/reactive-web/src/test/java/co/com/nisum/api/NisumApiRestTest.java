package co.com.nisum.api;

import co.com.nisum.api.handler.NisumApiHandler;
import co.com.nisum.api.model.UserPhoneView;
import co.com.nisum.api.model.UserRequestView;
import co.com.nisum.api.model.UserResponseView;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NisumApiRestTest {

    @InjectMocks
    private NisumApiRest nisumApiRest;

    @Mock
    private NisumApiHandler nisumApiHandler;

    @BeforeEach
    void setUp() {
        when(nisumApiHandler.save(any(UserRequestView.class)))
                .thenReturn(Mono.just(new UserResponseView()));
    }

    @AfterEach
    void tearDown() {
        verify(nisumApiHandler).save(any(UserRequestView.class));
    }

    @Test
    void saveUser() {
        var mono = nisumApiRest.saveUser(getUserRequestView());
        StepVerifier.create(mono).assertNext(Assertions::assertNotNull).verifyComplete();
    }

    private UserRequestView getUserRequestView() {
        var userPhone = UserPhoneView.builder()
                .cityCode("1")
                .countryCode("57")
                .number("2342466")
                .build();
        return UserRequestView.builder()
                .email("jandres314@gmail.com")
                .name("Jaime Andres Osorio Ramirez")
                .password("changeme")
                .phones(List.of(userPhone))
                .build();
    }

}
