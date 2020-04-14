package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.whddbs.sm.dao.PhotoBoardDao;

public class PhotoBoardDeleteServlet implements Servlet {
  
  PhotoBoardDao photoBoardDao;
  
  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("삭제할 회원 번호 : ");
    out.println("!{}!");
    int no = Integer.parseInt(in.nextLine());
    
    photoBoardDao.delete(no);
  }
}