package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.whddbs.sm.dao.PhotoBoardDao;
import com.whddbs.sm.domain.PhotoBoard;

public class PhotoBoardAddServlet implements Servlet {
  
  PhotoBoardDao photoBoardDao;
  
  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();
    
    out.println("회원 번호 : ");
    out.println("!{}!");
    photoBoard.setNo(Integer.parseInt(in.nextLine()));
    out.println("제목 : ");
    out.println("!{}!");
    photoBoard.setTitle(in.nextLine());
    
    if (photoBoardDao.insert(photoBoard) > 0) {
      out.println("입력되었습니다.");
    } else {
      out.println("입력 오류");
    }
  }
}
