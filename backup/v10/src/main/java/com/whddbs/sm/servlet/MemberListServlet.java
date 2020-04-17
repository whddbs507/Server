package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.domain.Member;

public class MemberListServlet implements Servlet {
  
  MemberDao memberDao;
  
  public MemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    List<Member> memberList = memberDao.findAll();
    
    for (Member m : memberList) {
      out.printf("%d %s %s %s\n", m.getNo(), m.getName(),
          m.getEmail(), m.getPw());
    }
  }
}