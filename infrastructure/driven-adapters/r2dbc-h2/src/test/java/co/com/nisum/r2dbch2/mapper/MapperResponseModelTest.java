package co.com.nisum.r2dbch2.mapper;

import co.com.nisum.model.user.UserResponse;
import co.com.nisum.r2dbch2.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MapperResponseModelTest {

    private Function<UserEntity, UserResponse> mapperResponseModel;

    @BeforeEach
    void setUp() {
        mapperResponseModel = new MapperResponseModel();
    }

    @Test
    void apply() {
        var entity = new UserEntity();
        var responseModel = mapperResponseModel.apply(entity);
        assertNotNull(responseModel);
    }

}
