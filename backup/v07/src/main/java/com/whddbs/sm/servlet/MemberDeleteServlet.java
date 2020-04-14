package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.json.MemberJsonFileDao;

public class MemberDeleteServlet implements Servlet {
  
  MemberDao memberDao;
  
  public MemberDeleteServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("삭제할 번호 : ");
    out.println("!{}!");
    int no = Integer.parseInt(in.nextLine());
    
    if (memberDao.delete(no) > 0) {
      out.println("삭제 완료");
    } else {
      out.println("해당 번호가 없습니다.");
    }
  }
}