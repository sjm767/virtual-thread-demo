package com.jaeshim.virtual.thread.springboot;

import com.jaeshim.virtual.thread.springboot.service.AsyncService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class TaskSchedulingExample {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(TaskSchedulingExample.class, args);
    AsyncService asyncService = (AsyncService) context.getBean("asyncService");

    List<CompletableFuture<Void>> futures = new ArrayList<>();

//    IntStream.range(0, 5).forEach(idx -> asyncService.async());
    IntStream.range(0, 5).forEach(idx -> {
      futures.add(asyncService.completableFutureAsync());
    });
    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    context.close();
  }

}
