package co.com.nisum.api.mapper;

import co.com.nisum.api.model.UserResponseView;
import co.com.nisum.model.user.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MapperResponseTest {

    private Function<UserResponse, UserResponseView> mapperResponse;

    @BeforeEach
    void setUp() {
        mapperResponse = new MapperResponse();
    }

    @Test
    void apply() {
        var responseView = mapperResponse.apply(new UserResponse());
        assertNotNull(responseView);
    }
}
