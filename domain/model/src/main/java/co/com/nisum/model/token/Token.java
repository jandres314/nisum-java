package co.com.nisum.model.token;

import lombok.*;

@Getter
@Builder(toBuilder = true)
public class Token {

    private final String secretKey;
    private final String keyId;
    private final String subject;
    private final String issuer;
    private final Integer secondsToExpiration;
}
