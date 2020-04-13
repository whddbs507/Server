package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.json.MemberJsonFileDao;
import com.whddbs.sm.domain.Member;

public class MemberAddServlet implements Servlet {
  
  MemberDao memberDao;
  
  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    Member member = (Member)in.readObject();

    if (memberDao.insert(member) > 0) {
      out.writeUTF("멤버가 추가되었습니다.");
      out.flush();
    } else {
      out.writeUTF("같은 번호의 회원이 있습니다.");
      out.flush();
    }
  }
}