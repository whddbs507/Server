package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.dao.json.BoardJsonFileDao;

public class BoardDeleteServlet implements Servlet {
  
  BoardDao boardDao;
  
  public BoardDeleteServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("삭제할 번호");
    out.println("!{}!");
    
    int no = Integer.parseInt(in.nextLine());
    
    if (boardDao.delete(no) > 0) {
      out.println("삭제되었습니다.");
    } else {
      out.println("해당 번호가 없어요");
    }
  }
}