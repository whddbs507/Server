package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public interface Servlet {
  default void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {}
  
  default void service(PrintStream out, Scanner in) throws Exception {}
}