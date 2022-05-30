package com.engineeringwithramaa.databasebackup;

import com.engineeringwithramaa.databasebackup.batch.DBProcessor;
import com.engineeringwithramaa.databasebackup.batch.DBReader;
import com.engineeringwithramaa.databasebackup.batch.DBWriter;
import com.engineeringwithramaa.databasebackup.model.backup_db.BUser;
import com.engineeringwithramaa.databasebackup.model.users_db.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    DBReader DBReader;
    @Autowired
    DBProcessor dbProcessor;
    @Autowired
    DBWriter dbWriter;

    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("Database Migration Step")
                .<User, BUser> chunk(1)
                .reader(DBReader)
                .processor(dbProcessor)
                .writer(dbWriter)
                .build();
    }

    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("Database Migration Job")
                .incrementer(new RunIdIncrementer())
                .flow(createStep()).end().build();
    }

}
