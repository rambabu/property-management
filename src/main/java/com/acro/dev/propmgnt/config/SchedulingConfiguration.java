package com.acro.dev.propmgnt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
@Configuration
@EnableScheduling
public class SchedulingConfiguration {
    @Scheduled(cron="0 05 10 15 * ?")
    public void ScheduleTaskUsingCronExpression(){
        long now=System.currentTimeMillis()/1000;
        System.out.println("Schedule tasks using cron jobs " + now);
    }
}