package co.com.nisum.api.consumer;

import co.com.nisum.model.user.exceptions.BusinessException;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PasswordConsumer implements Consumer<String> {

    private final Predicate<String> passwordPredicate;

    public PasswordConsumer(String passwordRegex) {
        passwordPredicate = password -> Pattern.matches(passwordRegex, password);
    }

    @Override
    public void accept(String password) {
        if (passwordPredicate.negate().test(password)) {
            throw new BusinessException("Password con formato invalido");
        }
    }

}
