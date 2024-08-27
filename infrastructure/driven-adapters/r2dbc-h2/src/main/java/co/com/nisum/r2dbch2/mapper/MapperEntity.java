package co.com.nisum.r2dbch2.mapper;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserPhone;
import co.com.nisum.r2dbch2.entity.UserEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.h2.util.json.JSONArray;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class MapperEntity implements BiFunction<User, String, UserEntity> {

    private final ObjectMapper objectMapper;

    @Override
    public UserEntity apply(User user, String token) {
        var localDateTime = LocalDateTime.now();
        return UserEntity.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .token(token)
                .modified(localDateTime)
                .lastlogin(localDateTime)
                .created(localDateTime)
                .active(true)
                .phones(user.getPhones().stream().map(this::toJson).toList())
                .build();
    }

    private String toJson(UserPhone userPhone) {
        var json = objectMapper.convertValue(userPhone, JsonNode.class).toString();
        return json;
    }

}
