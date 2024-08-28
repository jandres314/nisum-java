package co.com.nisum.r2dbch2.service;
import static org.mockito.Mockito.*;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.UserResponse;
import co.com.nisum.r2dbch2.entity.UserEntity;
import co.com.nisum.r2dbch2.mapper.MapperEntity;
import co.com.nisum.r2dbch2.mapper.MapperResponseModel;
import co.com.nisum.r2dbch2.repository.UserH2Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserH2Repository userH2Repository;

    @Mock
    private MapperEntity mapperEntity;

    @Mock
    private MapperResponseModel mapperResponseModel;

    @Test
    void save() {
        when(userH2Repository.save(any(UserEntity.class)))
                .thenReturn(Mono.just(new UserEntity()));
        when(mapperEntity.apply(any(User.class), anyString()))
                .thenReturn(new UserEntity());
        when(mapperResponseModel.apply(any(UserEntity.class)))
                .thenReturn(new UserResponse());
        var mono = userService.save(new User(), "token_jwt");
        StepVerifier.create(mono).assertNext(Assertions::assertNotNull).verifyComplete();
        verify(userH2Repository).save(any(UserEntity.class));
        verify(mapperEntity).apply(any(User.class), anyString());
        verify(mapperResponseModel).apply(any(UserEntity.class));
    }

    @Test
    void findUserIdByEmail() {
        var entity = UserEntity.builder().id(UUID.randomUUID()).build();
        when(userH2Repository.findUserEntityByEmail(anyString()))
                .thenReturn(Mono.just(entity));
        var mono = userService.findUserIdByEmail("jj@bancobci.cl");
        StepVerifier.create(mono).assertNext(Assertions::assertNotNull).verifyComplete();
        verify(userH2Repository).findUserEntityByEmail(anyString());
    }

}
