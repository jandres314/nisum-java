package co.com.nisum.api.mapper;

import co.com.nisum.api.model.UserResponseView;
import co.com.nisum.model.user.UserResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperResponse implements Function<UserResponse, UserResponseView> {

    @Override
    public UserResponseView apply(UserResponse userResponse) {
        return UserResponseView.builder()
                .token(userResponse.getToken())
                .createdAt(userResponse.getCreatedAt())
                .isActive(userResponse.getIsActive())
                .lastLoginAt(userResponse.getLastLoginAt())
                .modifiedAt(userResponse.getModifiedAt())
                .userId(userResponse.getUserId())
                .build();
    }

}
