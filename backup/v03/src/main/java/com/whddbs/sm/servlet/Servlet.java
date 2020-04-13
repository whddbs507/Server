package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Servlet {
  public abstract void service(ObjectOutputStream out, ObjectInputStream in) throws Exception;
}