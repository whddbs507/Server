package com.whddbs.sm.dao;

import java.util.List;
import com.whddbs.sm.domain.PhotoBoard;

public interface PhotoBoardDao {
  
  public int insert(PhotoBoard photoBoard) throws Exception;

  public List<PhotoBoard> findAll() throws Exception;

  public PhotoBoard findByNo(int no) throws Exception;

  public int delete(int no) throws Exception;

  default List<PhotoBoard> findByKeyword(String keyword) throws Exception {
    return null;
  }
}
