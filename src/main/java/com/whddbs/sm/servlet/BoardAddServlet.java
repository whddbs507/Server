package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.dao.json.BoardJsonFileDao;
import com.whddbs.sm.domain.Board;

public class BoardAddServlet implements Servlet {

  BoardDao boardDao;

  public BoardAddServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    out.reset();
    out.writeObject(boardDao.findAll());

    Board board = (Board)in.readObject();

    if (boardDao.insert(board) > 0) {
      out.writeUTF("OK");
      out.flush();
    } else {
      out.writeUTF("FAIL");
      out.flush();
    }
  }
}