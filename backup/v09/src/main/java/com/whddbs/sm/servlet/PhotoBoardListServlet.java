package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.PhotoBoardDao;
import com.whddbs.sm.domain.Member;
import com.whddbs.sm.domain.PhotoBoard;

public class PhotoBoardListServlet implements Servlet {
  
  PhotoBoardDao photoBoardDao;
  MemberDao memberDao;
  
  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao, MemberDao memberDao) {
    this.photoBoardDao = photoBoardDao;
    this.memberDao = memberDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("수업번호? ");
    out.println("!{}!");
    
    int memberNo = Integer.parseInt(in.nextLine());
    
    Member member = memberDao.findByNo(memberNo);
    
    List<PhotoBoard> photoBoardList = new ArrayList<>();
    
    out.printf("회원 성명 : %s\n", member.getName());
    out.println("--------------------------------");
    
    photoBoardList = photoBoardDao.findAllByMemberNo(memberNo);
    
    for (PhotoBoard p : photoBoardList) {
      out.printf("%d %s %s\n", p.getNo(), p.getTitle(), p.getContents());
    }
  }
}
