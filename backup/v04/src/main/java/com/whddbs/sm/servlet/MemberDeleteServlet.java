package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.json.MemberJsonFileDao;

public class MemberDeleteServlet implements Servlet {
  
  MemberDao memberDao;
  
  public MemberDeleteServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
      int no = in.readInt();

      if (memberDao.delete(no) > 0) {
        out.writeUTF("OK");
        out.flush();
      } else {
        out.writeUTF("해당 번호가 존재하지 않습니다");
        out.flush();
      }
  }
}