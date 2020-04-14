package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.json.MemberJsonFileDao;
import com.whddbs.sm.domain.Member;

public class MemberAddServlet implements Servlet {
  
  MemberDao memberDao;
  
  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    Member member = new Member();
    
    out.println("번호 : ");
    out.println("!{}!");
    member.setNo(Integer.parseInt(in.nextLine()));
    
    out.println("이름 : ");
    out.println("!{}!");
    member.setName(in.nextLine());
    
    out.println("이메일 : ");
    out.println("!{}!");
    member.setEmail(in.nextLine());
    
    out.println("비밀번호 : ");
    out.println("!{}!");
    member.setPw(in.nextLine());
    
    memberDao.insert(member);
  }
}