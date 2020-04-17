package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.whddbs.sm.DataLoaderObserver;
import com.whddbs.sm.dao.PhotoBoardDao;
import com.whddbs.sm.dao.PhotoFileDao;

public class PhotoBoardDeleteServlet implements Servlet {
  
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  
  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("삭제할 회원 번호 : ");
    out.println("!{}!");
    
    int no = Integer.parseInt(in.nextLine());
    
    DataLoaderObserver.con.setAutoCommit(false);
    
    photoFileDao.deleteAll(no);
    
    if (photoBoardDao.delete(no) > 0) {
      out.println("사진 게시글을 삭제했습니다.");
      DataLoaderObserver.con.commit();
    } else {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      DataLoaderObserver.con.rollback();
    }
    DataLoaderObserver.con.setAutoCommit(true);
  }
}