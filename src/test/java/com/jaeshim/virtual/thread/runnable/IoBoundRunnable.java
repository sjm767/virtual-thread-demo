package com.jaeshim.virtual.thread.runnable;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IoBoundRunnable implements Runnable{

  @Override
  public void run() {
    try {
      //5초간 Block
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
