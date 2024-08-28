package co.com.nisum.r2dbch2.mapper;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserPhone;
import co.com.nisum.r2dbch2.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MapperEntityTest {

    private BiFunction<User, String, UserEntity> mapperEntity;

    @BeforeEach
    void setUp() {
        mapperEntity = new MapperEntity(new ObjectMapper());
    }

    @Test
    void apply() {
        var token = "eyJraWQiOiJuaXN1bV9hcHBzIiwiYWxnIjoiSFM1MTIiLCJ0eXAiOiJKV1QifQ";
        var entity = mapperEntity.apply(getUser(), token);
        assertNotNull(entity);
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
