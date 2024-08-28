package co.com.nisum.api.mapper;

import co.com.nisum.api.model.UserPhoneView;
import co.com.nisum.api.model.UserRequestView;
import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserPhone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MapperRequestTest {

    private Function<UserRequestView, User> mapperRequest;

    @BeforeEach
    void setUp() {
        mapperRequest = new MapperRequest();
    }

    @Test
    void apply() {
        var user = mapperRequest.apply(getUserView());
        assertNotNull(user);
    }

    private UserRequestView getUserView() {
        return UserRequestView.builder()
                .email("jandres314@gmail.com")
                .name("Jaime Andres Osorio Ramirez")
                .password("changeme")
                .phones(List.of(new UserPhoneView()))
                .build();
    }

}
