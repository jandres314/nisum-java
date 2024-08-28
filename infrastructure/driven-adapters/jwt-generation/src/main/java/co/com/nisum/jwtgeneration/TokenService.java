package co.com.nisum.jwtgeneration;

import co.com.nisum.model.token.Token;
import co.com.nisum.model.token.gateways.TokenRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
public class TokenService implements TokenRepository {

    private final Token tokenParameters;

    @Override
    public Mono<String> createToken() {
        var algorithm = Algorithm.HMAC512(tokenParameters.getSecretKey());
        var token = JWT.create()
                .withIssuer(tokenParameters.getIssuer())
                .withExpiresAt(Instant.now().plusSeconds(tokenParameters.getSecondsToExpiration()))
                .withKeyId(tokenParameters.getKeyId())
                .withJWTId(UUID.randomUUID().toString())
                .withSubject(tokenParameters.getSubject())
                .sign(algorithm);
        return Mono.just(token);
    }

}
