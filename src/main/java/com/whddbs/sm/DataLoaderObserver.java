package com.whddbs.sm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import com.whddbs.sm.context.Observer;
import com.whddbs.sm.dao.mariadb.BoardDaoImpl;
import com.whddbs.sm.dao.mariadb.MemberDaoImpl;
import com.whddbs.sm.dao.mariadb.PhotoBoardDaoImpl;

public class DataLoaderObserver implements Observer {
  
  Connection con;
  
  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로드합니다.");
    
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/study2db", "study2", "1234");
      
      context.put("boardDao", new BoardDaoImpl(con));
      context.put("memberDao", new MemberDaoImpl(con));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(con));
      
    } catch (Exception e) {
      System.out.println("DataLoaderObserver 오류 : " + e.getMessage());
    }
  }
  
  @Override
  public void contextDestroyed(Map<String, Object> context) {
    try {
      con.close();
    } catch (Exception e) {
      
    }
  }
}