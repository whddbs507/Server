package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.dao.json.BoardJsonFileDao;

public class BoardDeleteServlet implements Servlet {
  
  BoardDao boardDao;
  
  public BoardDeleteServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {

    int no = in.readInt();
    
    if (boardDao.delete(no) > 0) {
      out.writeUTF("OK");
      out.flush();
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
      out.flush();
    }
  }
}