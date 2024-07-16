package com.jaeshim.virtual.thread.demo;

import com.jaeshim.virtual.thread.runnable.IoBoundRunnable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PureJavaIoBoundExample {

  private static final IoBoundRunnable ioBoundRunnable = new IoBoundRunnable();
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    log.info("작업 시작. {}", Thread.currentThread());

//    platformThreadWithIoBound();
    virtualThreadWithIoBound();

    long endTime = System.currentTimeMillis();
    log.info("작업 종료. {}ms {}", (endTime - startTime), Thread.currentThread());
  }

  private static void platformThreadWithIoBound() {
    try (ExecutorService executorService = Executors.newFixedThreadPool(10000)) {
      for (int i = 0; i < 10000; i++) {
        executorService.submit(ioBoundRunnable);
      }
    }
  }
  private static void virtualThreadWithIoBound(){
    ThreadFactory vtFactory = Thread.ofVirtual().name("virtual-thread", 0).factory();
    try (ExecutorService executorService = Executors.newThreadPerTaskExecutor(vtFactory)) {
      for (int i = 0; i < 10000; i++) {
        executorService.submit(ioBoundRunnable);
      }
    }
  }
}
