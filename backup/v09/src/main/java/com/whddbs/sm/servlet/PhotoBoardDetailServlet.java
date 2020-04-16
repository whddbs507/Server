package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.dao.PhotoBoardDao;
import com.whddbs.sm.dao.PhotoFileDao;
import com.whddbs.sm.domain.PhotoBoard;
import com.whddbs.sm.domain.PhotoFile;

public class PhotoBoardDetailServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDetailServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("확인할 회원 번호 : ");
    out.println("!{}!");
    int no = Integer.parseInt(in.nextLine());

    PhotoBoard photoBoard = photoBoardDao.findByNo(no);

    if (photoBoard != null) {

      out.printf("회원번호 : %d\n", photoBoard.getNo());
      out.printf("제목 : %s\n", photoBoard.getTitle());
      out.printf("내용 : %s\n", photoBoard.getContents());
      out.println("사진 파일 : ");

      List<PhotoFile> photoFiles = photoFileDao.findAll(photoBoard.getNo());
      for (PhotoFile photoFile : photoFiles) {
        out.printf(" > %s\n", photoFile.getFilepath());
      }
    }
  }
}