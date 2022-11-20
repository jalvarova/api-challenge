package com.pe.walavo.challenge.infraestructure.config;

import com.pe.walavo.challenge.domain.repository.ChampionshipRepository;
import com.pe.walavo.challenge.domain.repository.ConfigurationRepository;
import com.pe.walavo.challenge.domain.repository.MatchRepository;
import com.pe.walavo.challenge.domain.repository.PlayerRepository;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
@Slf4j
@EnableR2dbcRepositories(basePackageClasses = {
        PlayerRepository.class,
        MatchRepository.class,
        ChampionshipRepository.class,
        ConfigurationRepository.class
})
public class DatabaseConfig {

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;
    }

}
