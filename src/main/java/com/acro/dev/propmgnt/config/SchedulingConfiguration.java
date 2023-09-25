package com.acro.dev.propmgnt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {
    private static final Logger LOGGER=LoggerFactory.getLogger((SchedulingConfiguration.class));
    @Scheduled(cron="0 05 10 15 * ?")
    public void ScheduleTaskUsingCronExpression(){
        long now=System.currentTimeMillis()/1000;
        LOGGER.info("Schedule tasks using cron jobs " + now);
    }
}
