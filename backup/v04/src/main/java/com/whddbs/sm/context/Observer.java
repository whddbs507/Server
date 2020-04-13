package com.whddbs.sm.context;

import java.util.Map;

public interface Observer {
  public abstract void contextInitialized(Map<String, Object> context);
  public abstract void contextDestroyed(Map<String, Object> context);
}
