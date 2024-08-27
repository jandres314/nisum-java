package co.com.nisum.r2dbch2.mapper;

import co.com.nisum.model.user.User;
import co.com.nisum.r2dbch2.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class MapperEntity implements BiFunction<User, String, UserEntity> {

    @Override
    public UserEntity apply(User user, String token) {
        return UserEntity.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }

}
