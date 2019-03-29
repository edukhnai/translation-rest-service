package com.dukhnai.translationservice;

import javax.sql.DataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@TestConfiguration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .build();
    }
}
