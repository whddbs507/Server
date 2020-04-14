package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.domain.Board;

public class BoardListServlet implements Servlet {
  
  BoardDao boardDao;
  
  public BoardListServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    List<Board> boards = boardDao.findAll();
    
    for (Board b : boards) {
      out.printf("%d %s %s\n", b.getNo(), 
          b.getTitle(), b.getContents());
    }
  }
}