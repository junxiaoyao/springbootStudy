package com.jxy.config.scheduledConfig;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author: jxy
 * @create: 2019-09-16 09:56
 */
@Component
public class ScheduledService {

    @Scheduled(fixedDelay = 2000L)
    public void sayHello() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
        System.out.println("hello world,time is " + simpleDateFormat.format(Calendar.getInstance().getTime()));
    }
}
