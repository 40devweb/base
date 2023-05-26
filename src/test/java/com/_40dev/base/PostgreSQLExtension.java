package com._40dev.base;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLExtension implements BeforeAllCallback, AfterAllCallback {

    private PostgreSQLContainer<?> postgres;

    @Override
    public void beforeAll(ExtensionContext context) {
        postgres = new PostgreSQLContainer<>("postgres:15")
          .withDatabaseName("basedb")
          .withUsername("postgres")
          .withPassword("pass")
          .withExposedPorts(5432)
          .withInitScript("db/initdb.sql");

        postgres.start();
        String jdbcUrl = String.format("jdbc:postgresql://localhost:%d/basedb", postgres.getFirstMappedPort());
        System.setProperty("spring.datasource.url", jdbcUrl);
        System.setProperty("spring.datasource.username", "baseuser");
        System.setProperty("spring.datasource.password", "baseuser");

        System.setProperty("spring.liquibase.url",jdbcUrl);
        System.setProperty("spring.liquibase.username", "baseadmin");
        System.setProperty("spring.liquibase.password", "baseadmin");
        System.setProperty("spring.liquibase.change-log","classpath:/db/changelog/db.changelog-master.xml");
        System.setProperty("spring.liquibase.default-schema","baseadmin");

    }

    @Override
    public void afterAll(ExtensionContext context) {
        // do nothing, Testcontainers handles container shutdown
    }
}
