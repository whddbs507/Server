package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.whddbs.sm.dao.PhotoBoardDao;
import com.whddbs.sm.domain.PhotoBoard;

public class PhotoBoardDetailServlet implements Servlet {
  
  PhotoBoardDao photoBoardDao;
  
  public PhotoBoardDetailServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("확인할 회원 번호 : ");
    out.println("!{}!");
    int no = Integer.parseInt(in.nextLine());

    PhotoBoard photoboard = photoBoardDao.findByNo(no);

    out.printf("회원번호 : %d\n", photoboard.getNo());
    out.printf("제목 : %s\n", photoboard.getTitle());
    out.printf("내용 : %s\n", photoboard.getContents());
  }
}
