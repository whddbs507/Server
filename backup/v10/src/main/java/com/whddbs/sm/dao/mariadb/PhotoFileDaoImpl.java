package com.whddbs.sm.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.whddbs.sm.dao.PhotoFileDao;
import com.whddbs.sm.domain.PhotoFile;
import com.whddbs.sm.util.ConnectionFactory;

public class PhotoFileDaoImpl implements PhotoFileDao {
  
  ConnectionFactory conFactory;
  
  public PhotoFileDaoImpl(ConnectionFactory conFactory) {
    this.conFactory = conFactory;
  }
  
  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    Connection con = conFactory.getConnection();
    Statement stmt = con.createStatement();
    return stmt.executeUpdate("insert into photo_file(photo_id, file_path)"
        + " values(" + photoFile.getBoardNo() + ", '" + photoFile.getFilepath() + "')");
  }
  
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    Connection con = conFactory.getConnection();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select photo_file_id, photo_id, file_path"
        + " from photo_file where photo_id = " + boardNo
        + " order by photo_file_id asc");
    
    ArrayList<PhotoFile> list = new ArrayList<>();
    
    while (rs.next()) {
      list.add(new PhotoFile()
          .setNo(rs.getInt("photo_file_id"))
          .setFilepath(rs.getString("file_path"))
          .setBoardNo(rs.getInt("photo_id")));
    }
    return list;
  }

  public int deleteAll(int boardNo) throws Exception {
    Connection con = conFactory.getConnection();
    Statement stmt = con.createStatement();
    return stmt.executeUpdate("delete from photo_file"
        + " where photo_id = " + boardNo);
  }
}
