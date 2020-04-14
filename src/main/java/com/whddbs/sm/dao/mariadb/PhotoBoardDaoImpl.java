package com.whddbs.sm.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.whddbs.sm.dao.PhotoBoardDao;
import com.whddbs.sm.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {
  
  Connection con;
  
  public PhotoBoardDaoImpl(Connection con) {
    this.con = con;
  }
  
  public int insert(PhotoBoard photoBoard) throws Exception {
    Statement stmt = con.createStatement();
    
    return stmt.executeUpdate("insert into photoboard(member_id, titl)"
        + " values(" + photoBoard.getNo() + ", '" + photoBoard.getTitle()
        + "')");
  }

  public List<PhotoBoard> findAll() throws Exception {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select member_id, titl, cont from photoboard");
    
    List<PhotoBoard> photoBoardList = new ArrayList<>();
    
    while (rs.next()) {
      PhotoBoard photoBoard = new PhotoBoard();
      
      photoBoard.setNo(rs.getInt("member_id"));
      photoBoard.setTitle(rs.getString("titl"));
      photoBoard.setContents(rs.getString("cont"));
      
      photoBoardList.add(photoBoard);
    }
    return photoBoardList;
  }

  public PhotoBoard findByNo(int no) throws Exception {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select member_id, titl, cont from photoboard where member_id = " + no);
    
    if (rs.next()) {
      PhotoBoard photoBoard = new PhotoBoard();
      
      photoBoard.setNo(rs.getInt("member_id"));
      photoBoard.setTitle(rs.getString("titl"));
      photoBoard.setContents(rs.getString("cont"));
      
      return photoBoard;
    }
    return null;
  }

  public int delete(int no) throws Exception {
    Statement stmt = con.createStatement();
    
    return stmt.executeUpdate("delete from photoboard where member_id = " + no); 
  }

  public List<PhotoBoard> findByKeyword(String keyword) throws Exception {
    return null;
  }
}
