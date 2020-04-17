package com.whddbs.sm;

import java.sql.Connection;
import java.util.Map;
import com.whddbs.sm.context.Observer;
import com.whddbs.sm.dao.mariadb.BoardDaoImpl;
import com.whddbs.sm.dao.mariadb.MemberDaoImpl;
import com.whddbs.sm.dao.mariadb.PhotoBoardDaoImpl;
import com.whddbs.sm.dao.mariadb.PhotoFileDaoImpl;
import com.whddbs.sm.util.ConnectionFactory;

public class DataLoaderObserver implements Observer {
  
  public static Connection con;
  
  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로드합니다.");
    
    try {
      
      ConnectionFactory conFactory = new ConnectionFactory("jdbc:mariadb://localhost:3306/study2db", "study2", "1234");
      
      context.put("boardDao", new BoardDaoImpl(conFactory));
      context.put("memberDao", new MemberDaoImpl(conFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(conFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(conFactory));
      
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