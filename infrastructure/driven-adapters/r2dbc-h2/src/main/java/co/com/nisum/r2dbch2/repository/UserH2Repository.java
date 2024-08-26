package co.com.nisum.r2dbch2.repository;

import co.com.nisum.r2dbch2.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface UserH2Repository extends ReactiveCrudRepository<UserEntity, UUID> {

}
