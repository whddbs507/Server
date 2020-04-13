package com.whddbs.sm;

import java.util.Map;
import com.whddbs.sm.context.Observer;

public class GreetingObserver implements Observer {
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("====GreetingObserver 시작====");
  }
  public void contextDestroyed() {
    System.out.println("====GreetingObserver 종료====");
  }
}
