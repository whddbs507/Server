package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.whddbs.sm.dao.MemberDao;

public class MemberListServlet implements Servlet {
  
  MemberDao memberDao;
  
  public MemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
      in.readObject();
      out.writeUTF("서버로부터 회원 목록을 받았습니다.");
      out.reset();
      out.writeObject(memberDao.findAll()); 
  }
}