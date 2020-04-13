package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.dao.json.BoardJsonFileDao;
import com.whddbs.sm.domain.Board;

public class BoardDetailServlet implements Servlet {

  BoardDao boardDao;

  public BoardDetailServlet(BoardDao boardDao) {
    this.boardDao = boardDao; 
  }

  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no = in.readInt();

    Board board = boardDao.findByNo(no);
    
    if (board != null) {
      out.writeUTF("OK");
      out.writeObject(board);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 게시물 번호가 존재하지 않습니다.");
    }
  }
}