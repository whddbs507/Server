package com.whddbs.sm.dao;

import java.util.List;
import com.whddbs.sm.domain.Board;
import com.whddbs.sm.domain.Member;

public interface MemberDao {
  public int insert(Member member) throws Exception;
  public int delete(int no) throws Exception;
  public List<Member> findAll() throws Exception;
  public Member findByNo(int no) throws Exception;
  default List<Member> findByKeyword(String keyword) throws Exception {
    return null;
  }
}
