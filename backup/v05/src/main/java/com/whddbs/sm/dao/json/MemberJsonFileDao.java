package com.whddbs.sm.dao.json;

import java.util.List;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.domain.Member;

public class MemberJsonFileDao extends AbstractJsonFileDao<Member> implements MemberDao {
  
  public MemberJsonFileDao(String filename) {
    super(filename);
  }
  
  public int insert(Member member) {
    for (int i = 0; i < list.size(); i++) {
      if (indexOf(member.getNo()) > -1)
      return 0;
    }
    
    list.add(member);
    saveData();
    return 1;
  }
  
  public int delete(int no) {
    int index = indexOf(no);
    
    if (index == -1) {
      return 0;
    }
    
    list.remove(index);
    saveData();
    return 1;
  }
  
  public List<Member> findAll() {
    return list;
  }
  
  public Member findByNo(int no) {
    int index = indexOf(no);
    
    if (index != -1) {
      return list.get(index);
    } else {
      return null;
    }
  }
  
  @Override
  protected <K> int indexOf(K key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int)key) {
        return i;
      }
    }
    return -1;
  }
}