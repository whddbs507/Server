package com.whddbs.sm.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.dao.json.BoardJsonFileDao;
import com.whddbs.sm.domain.Board;

public class BoardDetailServlet implements Servlet {

  BoardDao boardDao;

  public BoardDetailServlet(BoardDao boardDao) {
    this.boardDao = boardDao; 
  }

  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("확인할 게시판 번호 : ");
    out.println("!{}!");
    
    int no = Integer.parseInt(in.nextLine());
    Board board = boardDao.findByNo(no);
    
    if (board != null) {
      out.printf("번호 : %d\n", board.getNo());
      out.printf("제목 : %s\n", board.getTitle());
      out.printf("내용 : %s\n", board.getContents());
    } else {
      out.println("해당 번호가 없습니다.");
    }
  }
}