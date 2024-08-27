package co.com.nisum.api.mapper;

import co.com.nisum.api.model.UserPhoneView;
import co.com.nisum.api.model.UserRequestView;
import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserPhone;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperRequest implements Function<UserRequestView, User> {

    @Override
    public User apply(UserRequestView userRequestView) {
        return User.builder()
                .email(userRequestView.getEmail())
                .name(userRequestView.getName())
                .password(userRequestView.getPassword())
                .phones(userRequestView.getPhones().stream().map(this::mapUserPhone).toList())
                .build();
    }

    private UserPhone mapUserPhone(UserPhoneView userPhoneView) {
        return UserPhone.builder()
                .cityCode(userPhoneView.getCityCode())
                .countryCode(userPhoneView.getCountryCode())
                .number(userPhoneView.getNumber())
                .build();
    }

}
