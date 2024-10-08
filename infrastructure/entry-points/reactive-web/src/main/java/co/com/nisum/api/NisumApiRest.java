package co.com.nisum.api;

import co.com.nisum.api.handler.NisumApiHandler;
import co.com.nisum.api.model.UserRequestView;
import co.com.nisum.api.model.UserResponseView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class NisumApiRest {

    private final NisumApiHandler nisumApiHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserResponseView> saveUser(@RequestBody @Valid UserRequestView body) {
        var init = Instant.now();
        return nisumApiHandler.save(body)
                .doFirst(() -> log.info("body: {}", body))
                .doOnSuccess(response -> log.info("201 (CREATED): {}", response))
                .doAfterTerminate(() ->
                        log.info("time for request procesed in milis: {}",
                                ChronoUnit.MILLIS.between(init, Instant.now())));
    }

}
