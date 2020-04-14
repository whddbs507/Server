package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.domain.Member;

public class MemberSearchServlet implements Servlet {
  MemberDao memberDao;
  
  public MemberSearchServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("키워드 입력 : ");
    out.println("!{}!");
    String keyword = in.nextLine();
    
    List<Member> memberList = memberDao.findByKeyword(keyword);
    
    if (!memberList.isEmpty()) {
      for (Member m : memberList) {
        out.printf("번호 : %d\n", m.getNo());
        out.printf("이름 : %s\n", m.getName());
        out.printf("이메일 : %s\n", m.getEmail());
        out.printf("비밀번호 : %s\n", m.getPw());
      }
    } else {
      out.println("키워드가 존재하지 않습니다.");
    }
  }
}
