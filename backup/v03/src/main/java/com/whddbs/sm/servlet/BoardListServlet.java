package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.dao.json.BoardJsonFileDao;

public class BoardListServlet implements Servlet {
  
  BoardDao boardDao;
  
  public BoardListServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    out.writeUTF("서버로부터 게시판 목록을 받았습니다.");
    out.reset();
    out.writeObject(boardDao.findAll());
  }
}