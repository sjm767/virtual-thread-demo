package com.jaeshim.virtual.thread.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VirtualScheduler {

  //5초에 한 번 씩 수행
  @Scheduled(fixedRate =  5000)
  public void fixedRate() {
    log.info("fixed Rate. current thread is {}", Thread.currentThread());
  }
}
