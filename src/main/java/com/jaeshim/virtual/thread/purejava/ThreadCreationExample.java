package com.jaeshim.virtual.thread.purejava;

import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadCreationExample {

  public static void main(String[] args) {
    normalThreadCreationTest();


//    virtualThreadCreationTest();
  }

  private static void normalThreadCreationTest() {
    long start = System.currentTimeMillis();
    List<Thread> threads = IntStream.range(0, 1000000)
        .mapToObj(i -> new Thread(()->{}))
        .toList();

    threads.forEach(Thread::start);

    long end = System.currentTimeMillis();

    System.out.println("소요 시간: " + (end - start) + "ms");
  }

  private static void virtualThreadCreationTest() {
    long start = System.currentTimeMillis();
    List<Thread> threads = IntStream.range(0, 1000000)
        .mapToObj(i -> Thread.ofVirtual().unstarted(()->{}))
        .toList();

    threads.forEach(Thread::start);
    long end = System.currentTimeMillis();

    System.out.println("소요 시간: " + (end - start) + "ms");

  }
}
