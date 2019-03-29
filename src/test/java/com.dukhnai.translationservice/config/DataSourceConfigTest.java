package com.dukhnai.translationservice.config;

import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "/application.properties")
public class DataSourceConfigTest {
    private Environment environment;
    private DataSourceConfig dataSourceConfig;

    @Before
    public void setUp() {
        this.environment = mock(Environment.class);

        when(environment.getProperty(environment.getProperty("spring.datasource.url"))).thenReturn("jdbc:h2:tcp://localhost/~/test");
        when(environment.getProperty("spring.datasource.username")).thenReturn("sa");
        when(environment.getProperty("spring.datasource.password")).thenReturn("");
        when(environment.getProperty("spring.datasource.driver-class-name")).thenReturn("org.h2.Driver");

        this.dataSourceConfig = new DataSourceConfig(environment);
    }

    @Test
    public void shouldReturnDataSourceInstanceWhenMethodCalled() {
        DataSource returnedInstance = dataSourceConfig.dataSource();

        assertNotNull(returnedInstance);
    }
}
