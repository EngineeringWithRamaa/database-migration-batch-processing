package com.engineeringwithramaa.databasebackup.configuration;

import com.engineeringwithramaa.databasebackup.model.users_db.User;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.engineeringwithramaa.databasebackup.repository.users-db",
        entityManagerFactoryRef = "userDbEntityManagerFactory",
        transactionManagerRef= "userDbTransactionManager")
public class UserDbDataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.users-db")
    public DataSourceProperties userDbDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.users-db.configuration")
    public DataSource userDbDataSource() {
        return userDbDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }
    @Primary
    @Bean(name = "userDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userDbEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(userDbDataSource())
                .packages(User.class)
                .build();
    }
    @Primary
    @Bean
    public PlatformTransactionManager userDbTransactionManager(
            final @Qualifier("userDbEntityManagerFactory") LocalContainerEntityManagerFactoryBean userDbEntityManagerFactory) {
        return new JpaTransactionManager(userDbEntityManagerFactory.getObject());
    }
}