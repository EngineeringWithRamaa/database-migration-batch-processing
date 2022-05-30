package com.engineeringwithramaa.databasebackup.configuration;

import com.engineeringwithramaa.databasebackup.model.backup_db.BUser;
import com.engineeringwithramaa.databasebackup.model.users_db.User;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.engineeringwithramaa.databasebackup.repository.backup_db",
        entityManagerFactoryRef = "backupDbEntityManagerFactory",
        transactionManagerRef= "backupDbTransactionManager")
public class BackupDbDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("app.datasource.backup-db")
    public DataSourceProperties backupDbDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    @ConfigurationProperties("app.datasource.backup-db.configuration")
    public DataSource backupDbDataSource() {
        return backupDbDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "backupDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean backupDbEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(backupDbDataSource())
                .packages(BUser.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager backupDbTransactionManager(
            final @Qualifier("backupDbEntityManagerFactory") LocalContainerEntityManagerFactoryBean backupDbEntityManagerFactory) {
        return new JpaTransactionManager(backupDbEntityManagerFactory.getObject());
    }
}
