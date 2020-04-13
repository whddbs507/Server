package com.whddbs.sm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.whddbs.sm.context.Observer;
import com.whddbs.sm.dao.json.BoardJsonFileDao;
import com.whddbs.sm.dao.json.MemberJsonFileDao;
import com.whddbs.sm.domain.Board;
import com.whddbs.sm.domain.Member;

public class DataLoaderObserver implements Observer {
  
  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로드합니다.");
    
    context.put("boardDao", new BoardJsonFileDao("./board.json"));
    context.put("memberDao", new MemberJsonFileDao("./member.json"));
  }
  
  @Override
  public void contextDestroyed(Map<String, Object> context) {
  }
}