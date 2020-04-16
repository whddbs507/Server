package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.dao.json.BoardJsonFileDao;
import com.whddbs.sm.domain.Board;

public class BoardAddServlet implements Servlet {

  BoardDao boardDao;
  Scanner keyboard;
  
  public BoardAddServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  public void service(PrintStream out, Scanner in) throws Exception {
    
    Board board = new Board();
    
    out.println("제목 : ");
    out.println("!{}!");
    board.setTitle(in.nextLine());
    
    
    out.println("내용 : ");
    out.println("!{}!");
    board.setContents(in.nextLine());
    
    boardDao.insert(board);
    
    out.println("추가되었습니다.");
  }
}