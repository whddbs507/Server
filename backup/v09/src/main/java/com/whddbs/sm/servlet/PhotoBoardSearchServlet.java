package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.whddbs.sm.dao.PhotoBoardDao;

public class PhotoBoardSearchServlet implements Servlet {
  
  PhotoBoardDao photoBoardDao;
  
  public PhotoBoardSearchServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {}
}
