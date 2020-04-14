package com.whddbs.sm.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.domain.Board;

public class BoardSearchServlet implements Servlet {
  BoardDao boardDao;
  
  public BoardSearchServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("키워드 입력 : ");
    out.println("!{}!");
    List<Board> boardList = new ArrayList<>();
    
    boardList = boardDao.findByKeyword(in.nextLine());
    
    if(boardList != null) {
      for (Board b : boardList) {
        out.printf("번호 : %d\n", b.getNo());
        out.printf("제목 : %s\n", b.getTitle());
        out.printf("내용 : %s\n", b.getContents());
      }
    } else {
      out.println("해당 키워드가 존재하지 않습니다!");
    }
  }
}
