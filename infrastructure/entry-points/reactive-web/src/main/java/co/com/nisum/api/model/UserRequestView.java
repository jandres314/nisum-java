package co.com.nisum.api.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserRequestView {

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z ]+$")
    @Size(max = 100)
    private String name;

    @NotEmpty
    @Size(max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.cl$")
    private String email;

    @NotEmpty
    @Size(max = 50)
    private String password;

    @Valid
    @Size(min = 1, max = 50)
    private List<UserPhoneView> phones;
}
