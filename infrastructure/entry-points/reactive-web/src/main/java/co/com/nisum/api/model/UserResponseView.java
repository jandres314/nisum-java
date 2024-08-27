package co.com.nisum.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseView {

    @JsonProperty("id")
    private UUID userId;
    @JsonProperty("created")
    private LocalDateTime createdAt;
    @JsonProperty("modified")
    private LocalDateTime modifiedAt;
    @JsonProperty("last_login")
    private LocalDateTime lastLoginAt;
    private String token;
    @JsonProperty("isactive")
    private Boolean isActive;
}
