package co.com.nisum.usecase.token;

import static org.mockito.Mockito.*;
import co.com.nisum.model.token.gateways.TokenRepository;
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
class TokenUseCaseTest {

    @InjectMocks
    private TokenUseCase tokenUseCase;

    @Mock
    private TokenRepository tokenRepository;

    @BeforeEach
    void setUp() {
        var token = "eyJraWQiOiJuaXN1bV9hcHBzIiwiYWxnIjoiSFM1MTIiLCJ0eXAiOiJKV1QifQ";
        when(tokenRepository.createToken()).thenReturn(Mono.just(token));
    }

    @AfterEach
    void tearDown() {
        verify(tokenRepository).createToken();
    }

    @Test
    void createToken() {
        var mono = tokenUseCase.createToken();
        StepVerifier.create(mono).assertNext(Assertions::assertNotNull).verifyComplete();
    }

}
