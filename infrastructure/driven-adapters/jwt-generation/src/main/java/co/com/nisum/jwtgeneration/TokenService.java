package co.com.nisum.jwtgeneration;

import co.com.nisum.model.user.gateways.UserToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
public class TokenService implements UserToken {

    @Value("${app.token.secretKey}")
    private String secretKeyToken;

    @Value("${app.token.keyId}")
    private String keyIdToken;

    @Value("${app.token.subject}")
    private String subjectToken;

    @Value("${app.token.issuer}")
    private String issuerToken;

    @Value("${app.token.secondsToExpiration}")
    private Integer secondsToExpiration;

    @Override
    public Mono<String> createToken() {
        var algorithm = Algorithm.HMAC512(secretKeyToken);
        var token = JWT.create()
                .withIssuer(issuerToken)
                .withExpiresAt(Instant.now().plusSeconds(secondsToExpiration))
                .withKeyId(keyIdToken)
                .withJWTId(UUID.randomUUID().toString())
                .withSubject(subjectToken)
                .sign(algorithm);
        return Mono.just(token);
    }

}
