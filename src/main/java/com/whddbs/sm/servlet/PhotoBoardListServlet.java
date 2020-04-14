package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.dao.PhotoBoardDao;
import com.whddbs.sm.domain.PhotoBoard;

public class PhotoBoardListServlet implements Servlet {
  
  PhotoBoardDao photoBoardDao;
  
  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    List<PhotoBoard> photoBoardList = new ArrayList<>();
    
    photoBoardList = photoBoardDao.findAll();
    
    for (PhotoBoard p : photoBoardList) {
      out.printf("%d %s %s\n", p.getNo(), p.getTitle(), p.getContents());
    }
  }
}
