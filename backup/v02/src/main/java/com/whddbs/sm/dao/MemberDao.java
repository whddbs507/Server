package com.whddbs.sm.dao;

import java.util.List;
import com.whddbs.sm.domain.Member;

public interface MemberDao {
  public int insert(Member member);
  public int delete(int no);
  public List<Member> findAll();
  public Member findByNo(int no);
}
