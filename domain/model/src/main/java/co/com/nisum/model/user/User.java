package co.com.nisum.model.user;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String name;
    private String email;
    private String password;
    private List<UserPhone> phones;
}
