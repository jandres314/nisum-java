package co.com.nisum.r2dbch2.config;

import io.r2dbc.h2.H2ConnectionConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import io.r2dbc.h2.H2ConnectionFactory;

@Configuration
@EnableR2dbcRepositories(basePackages = "co.com.nisum.r2dbch2.repository")
public class R2DBCConfiguration {

    @Bean
    public H2ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .url("mem:testdb;DB_CLOSE_DELAY=-1")
                        .username("sa")
                        .build());
    }

}
