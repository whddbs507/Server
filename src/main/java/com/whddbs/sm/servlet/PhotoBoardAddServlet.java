package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.PhotoBoardDao;
import com.whddbs.sm.dao.PhotoFileDao;
import com.whddbs.sm.domain.Member;
import com.whddbs.sm.domain.PhotoBoard;
import com.whddbs.sm.domain.PhotoFile;
import com.whddbs.sm.util.ConnectionFactory;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  MemberDao memberDao;
  PhotoFileDao photoFileDao;
  ConnectionFactory conFactory;

  public PhotoBoardAddServlet(ConnectionFactory conFactory, PhotoBoardDao photoBoardDao, MemberDao memberDao, PhotoFileDao photoFileDao) {
    this.conFactory = conFactory;
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

    Connection con = conFactory.getConnection();

    con.setAutoCommit(false);

    try {
      if (photoBoardDao.insert(photoBoard) == 0) {
        throw new Exception("사진 게시글 등록에 실패했어요.");
      }
      List<PhotoFile> photoFiles = inputPhotoFiles(in, out);

      for (PhotoFile photoFile : photoFiles) {
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
      }
      con.commit();
      out.println("새 사진 게시글을 등록했습니다.");
    } catch (Exception e) {
      con.rollback();
      out.println(e.getMessage());
    } finally {
      con.setAutoCommit(true);
    }
  }

  private List<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {

    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();

    while (true) {
      out.println("사진 파일? ");
      out.println("!{}!");

      String filepath = in.nextLine();
      System.out.println("2");
      if (filepath.length() == 0) {
        if (photoFiles.size() > 0) {
          break;
        } else {
          out.println("최소 한 개의 파일을 등록해야 합니다.");
          continue;
        }
      }    
      photoFiles.add(new PhotoFile().setFilepath(filepath));
      System.out.println("3");
    }

    return photoFiles;
  }
}
