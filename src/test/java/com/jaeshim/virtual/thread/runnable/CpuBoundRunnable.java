package com.jaeshim.virtual.thread.runnable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CpuBoundRunnable implements Runnable{

  @Override
  public void run() {
    log.info("run. thread {}", Thread.currentThread());
    long sum = 0;
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      sum = sum + i;
    }
    log.info(" run. sum : " + sum + ", thread {}", Thread.currentThread());
  }
}
