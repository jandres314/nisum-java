package co.com.nisum.r2dbch2.service;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserResponse;
import co.com.nisum.model.user.gateways.UserRepository;
import co.com.nisum.r2dbch2.mapper.MapperEntity;
import co.com.nisum.r2dbch2.mapper.MapperResponseModel;
import co.com.nisum.r2dbch2.repository.UserH2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserRepository {

    private final UserH2Repository userH2Repository;
    private final MapperEntity mapperEntity;
    private final MapperResponseModel mapperResponseModel;

    @Override
    public Mono<UserResponse> save(User user, String token) {
        return userH2Repository.save(mapperEntity.apply(user, token))
                .map(mapperResponseModel);
    }

    @Override
    public Mono<UUID> findUserIdByEmail(String email) {
        return Mono.empty();
    }
}
