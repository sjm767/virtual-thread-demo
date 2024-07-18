package com.jaeshim.virtual.thread.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class TaskSchedulingExample {

  public static void main(String[] args) {
    SpringApplication.run(TaskSchedulingExample.class, args);
  }

}
