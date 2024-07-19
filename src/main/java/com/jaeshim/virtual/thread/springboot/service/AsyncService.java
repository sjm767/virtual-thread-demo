package com.jaeshim.virtual.thread.springboot.service;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncService {

  @Autowired
  private Executor virtualThreadExecutor;

  @Async("threadPoolExecutor")
  public void async(){
    log.info("1) async. thread: {}", Thread.currentThread());

    try{
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    log.info("2) async end. thread: {}", Thread.currentThread());
  }

  public  CompletableFuture<Void> completableFutureAsync(){

    return CompletableFuture.runAsync(() -> {
      try {
        log.info("1) completableFutureAsnyc thread: {}", Thread.currentThread());
        Thread.sleep(3000);
        log.info("2) completableFutureAsnyc end. thread: {}", Thread.currentThread());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }, virtualThreadExecutor);
  }

}
