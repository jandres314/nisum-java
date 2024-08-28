package co.com.nisum.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserPhoneView {

    @Pattern(regexp = "^\\d{7,10}$")
    @NotEmpty
    private String number;

    @Pattern(regexp = "^\\d{1,5}$")
    @NotEmpty
    @JsonProperty("citycode")
    private String cityCode;

    @Pattern(regexp = "^\\d{1,5}$")
    @NotEmpty
    @JsonAlias({"countrycode", "contrycode"})
    private String countryCode;
}
