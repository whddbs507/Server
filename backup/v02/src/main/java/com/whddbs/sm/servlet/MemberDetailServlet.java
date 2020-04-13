package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.json.MemberJsonFileDao;
import com.whddbs.sm.domain.Member;

public class MemberDetailServlet implements Servlet {
  
  MemberDao memberDao;
  
  public MemberDetailServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {

    int no = in.readInt();
    
    Member member = memberDao.findByNo(no);
    
    if (member != null) {
      out.writeUTF("OK");
      out.writeObject(member);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("회원 중 해당 번호가 존재하지 않습니다.");
      out.flush();
    }
  }
}