package com.example.demo.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class TestJob {

    @Scheduled(cron = "0/50 * * * * ?")
    public void task1(){
        System.out.println("task1 start:" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }

    @Scheduled(cron = "0/50 * * * * ?")
    public void task2(){
        System.out.println("task2 start:" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }
}
