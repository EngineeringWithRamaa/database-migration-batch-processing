package com.engineeringwithramaa.databasebackup;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Configuration
@EnableScheduling
public class Scheduler {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job job;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    @Scheduled(cron="0 25 6 30 * *")
    public void scheduleDatabaseMigrationJob() throws Exception {
        System.out.println("****** DATABASE MIGRATION - BATCH PROCESSING - UNDERWAY ******");
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("time", format.format(Calendar.getInstance().getTime())).toJobParameters();
        jobLauncher.run(job, jobParameters);
        System.out.println("****** DATABASE MIGRATION - DONE ******\n");
    }
}
