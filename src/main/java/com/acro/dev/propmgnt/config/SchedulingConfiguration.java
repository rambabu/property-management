package com.acro.dev.propmgnt.config;
import com.acro.dev.propmgnt.advice.CommonExceptionHandler;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {
    private static final Logger LOGGER=LoggerFactory.getLogger((CommonExceptionHandler.class));
    @Scheduled(cron="0 05 10 15 * ?")
    public void ScheduleTaskUsingCronExpression(){
        long now=System.currentTimeMillis()/1000;
        LOGGER.info("Schedule tasks using cron jobs " + now);
    }
}
