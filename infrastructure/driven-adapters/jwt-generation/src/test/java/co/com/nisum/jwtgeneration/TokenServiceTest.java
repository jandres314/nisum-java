package co.com.nisum.jwtgeneration;

import co.com.nisum.model.token.Token;
import co.com.nisum.model.token.gateways.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenServiceTest {

    private TokenRepository tokenRepository;

    @BeforeEach
    void setUp() {
        var tokenParameters = Token.builder()
                .secretKey("secretKeyJwt")
                .keyId("nisum_apps")
                .subject("jwt_nissum_subject")
                .issuer("auth0")
                .secondsToExpiration(900)
                .build();
        tokenRepository = new TokenService(tokenParameters);
    }

    @Test
    void createToken() {
        var mono = tokenRepository.createToken();
        StepVerifier.create(mono).assertNext(s -> {
            assertNotNull(s);
            assertTrue(s.length() > 200);
        }).verifyComplete();
    }

}


