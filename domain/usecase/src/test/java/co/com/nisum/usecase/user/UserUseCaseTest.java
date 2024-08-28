package co.com.nisum.usecase.user;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserPhone;
import co.com.nisum.model.user.UserResponse;
import co.com.nisum.model.user.exceptions.BusinessException;
import co.com.nisum.model.user.gateways.UserRepository;
import co.com.nisum.usecase.token.TokenUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenUseCase tokenUseCase;

    @AfterEach
    void tearDown() {
        verify(userRepository).findUserIdByEmail(anyString());
    }

    @Test
    void saveUserWithExistingEmail() {
        when(userRepository.findUserIdByEmail(anyString()))
                .thenReturn(Mono.just(UUID.randomUUID()));
        var mono = userUseCase.save(getUser());
        StepVerifier.create(mono).expectError(BusinessException.class).verify();
    }

    @Test
    void saveUserSuccess() {
        when(userRepository.findUserIdByEmail(anyString()))
                .thenReturn(Mono.empty());
        when(tokenUseCase.createToken())
                .thenReturn(Mono.just(UUID.randomUUID().toString()));
        when(userRepository.save(any(User.class), anyString()))
                .thenReturn(Mono.just(new UserResponse()));
        var mono = userUseCase.save(getUser());
        StepVerifier.create(mono).assertNext(Assertions::assertNotNull).verifyComplete();
        verify(tokenUseCase).createToken();
        verify(userRepository).save(any(User.class), anyString());
    }

    private User getUser() {
        return User.builder()
                .email("jandres314@gmail.com")
                .name("Jaime Andres Osorio Ramirez")
                .password("changeme")
                .phones(List.of(new UserPhone()))
                .build();
    }

}
