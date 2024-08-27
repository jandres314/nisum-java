package co.com.nisum.r2dbch2.mapper;

import co.com.nisum.model.user.UserResponse;
import co.com.nisum.r2dbch2.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperResponseModel implements Function<UserEntity, UserResponse> {

    @Override
    public UserResponse apply(UserEntity userEntity) {
        return UserResponse.builder()
                .userId(userEntity.getId())
                .build();
    }

}
