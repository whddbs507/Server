package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import com.whddbs.sm.DataLoaderObserver;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.PhotoBoardDao;
import com.whddbs.sm.dao.PhotoFileDao;
import com.whddbs.sm.domain.Member;
import com.whddbs.sm.domain.PhotoBoard;
import com.whddbs.sm.domain.PhotoFile;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  MemberDao memberDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao, MemberDao memberDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.memberDao = memberDao;
    this.photoFileDao = photoFileDao;
  }

  public void service(PrintStream out, Scanner in) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();

    out.println("제목 : ");
    out.println("!{}!");
    photoBoard.setTitle(in.nextLine());

    out.println("회원 번호 : ");
    out.println("!{}!");
    int memberNo = Integer.parseInt(in.nextLine());

    Member member = memberDao.findByNo(memberNo);

    if (member == null) {
      out.println("회원 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setMember(member);

    DataLoaderObserver.con.setAutoCommit(false);
    
    if (photoBoardDao.insert(photoBoard) > 0) {
      out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

      ArrayList<PhotoFile> photoFiles = new ArrayList<>();

      while (true) {
        out.println("사진 파일? ");
        out.println("!{}!");

        String filepath = in.nextLine();

        if (filepath.length() == 0) {
          if (photoFiles.size() > 0) {
            break;
          } else {
            out.println("최소 한 개의 파일을 등록해야 합니다.");
            continue;
          }
        }
        photoFiles.add(new PhotoFile().setFilepath(filepath)
            .setBoardNo(photoBoard.getNo()));
      }
      for (PhotoFile photoFile : photoFiles) {
        photoFileDao.insert(photoFile);
      }
      DataLoaderObserver.con.commit();
      out.println("새 사진 게시글을 등록했습니다");
    } else {
      DataLoaderObserver.con.rollback();
      out.println("사진 게시글 등록 실패");
    }
    DataLoaderObserver.con.setAutoCommit(true);
  }
}
