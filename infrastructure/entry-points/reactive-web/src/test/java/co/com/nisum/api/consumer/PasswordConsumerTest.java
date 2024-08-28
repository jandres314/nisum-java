package co.com.nisum.api.consumer;

import co.com.nisum.model.user.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class PasswordConsumerTest {

    private Consumer<String> passwordConsumer;

    @BeforeEach
    void setUp() {
        passwordConsumer = new PasswordConsumer("^\\w{5,50}$");
    }

    @Test
    void acceptInvalidPaasword() {
        assertThrowsExactly(BusinessException.class,
                () -> passwordConsumer.accept("abcd@!#"),
                "Password con formato invalido");
    }

    @Test
    void accetOk() {
        passwordConsumer.accept("changeme");
        assertNotNull(passwordConsumer);
    }

}
