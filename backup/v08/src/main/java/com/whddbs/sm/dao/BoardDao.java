package com.whddbs.sm.dao;

import java.util.List;
import com.whddbs.sm.domain.Board;

public interface BoardDao {
  
  public int insert(Board board) throws Exception;
  
  public List<Board> findAll() throws Exception;
  
  public Board findByNo(int no) throws Exception;
  
  public int delete(int no) throws Exception;
  
  default List<Board> findByKeyword(String keyword) throws Exception {
    return null;
  }
}