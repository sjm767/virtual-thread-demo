package com.jaeshim.virtual.thread.demo;

import com.jaeshim.virtual.thread.runnable.CpuBoundRunnable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PureJavaCpuBoundExample {

  private static final CpuBoundRunnable cpuBoundRunnable = new CpuBoundRunnable();

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    log.info("작업 시작. {}", Thread.currentThread());

    //기존 방식
//    platformThreadWithCpuBound();
    //virtual thread 활용
    virtualThreadWithCpuBound();

    long endTime = System.currentTimeMillis();
    log.info("작업 종료. {}ms {}", (endTime - startTime), Thread.currentThread());

  }
  private static void platformThreadWithCpuBound() {
    // 100개 스레드 생성 및 100개의 작업 제출
    try (ExecutorService executorService = Executors.newFixedThreadPool(100)) {
      for (int i = 0; i < 100; i++) {
        executorService.submit(cpuBoundRunnable);
      }
    }
  }
  private static void virtualThreadWithCpuBound(){
    //virtual thread 생성 및 100개 작업 제출
    ThreadFactory vtFactory = Thread.ofVirtual().name("virtual-thread", 0).factory();
    try (ExecutorService executorService = Executors.newThreadPerTaskExecutor(vtFactory)) {
      for (int i = 0; i < 100; i++) {
        executorService.submit(cpuBoundRunnable);
      }
    }
  }
}
