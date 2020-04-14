package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.json.MemberJsonFileDao;
import com.whddbs.sm.domain.Member;

public class MemberDetailServlet implements Servlet {
  
  MemberDao memberDao;
  
  public MemberDetailServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {

    out.println("번호 입력 : ");
    out.println("!{}!");
    int no = Integer.parseInt(in.nextLine());
    
    Member member = memberDao.findByNo(no);
    
    if (member != null) {
      out.printf("번호 : %d\n", member.getNo());
      out.printf("이름 : %s\n", member.getName());
      out.printf("이메일 : %s\n", member.getEmail());
      out.printf("비밀번호 : %s\n", member.getPw());
    } else {
      out.println("해당 번호가 없어요..");
    }
  }
}