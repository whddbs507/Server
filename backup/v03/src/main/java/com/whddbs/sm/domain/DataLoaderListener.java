package com.whddbs.sm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.whddbs.sm.context.ApplicationContextListener;
import com.whddbs.sm.domain.Board;
import com.whddbs.sm.domain.Member;

public class DataLoaderListener implements ApplicationContextListener {
  List<Board> boardList = new ArrayList<>();
  List<Member> memberList = new ArrayList<>();
  
  
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로드합니다.");
    
    loadBoardData();
    loadMemberData();

    context.put("boardList", boardList);
    context.put("memberList", memberList);
  }
  
  public void contextDestroyed() {
    System.out.println("데이터를 저장합니다.");
    
    saveBoardData();
    saveMemberData();
  }
  
  private void loadBoardData() {
    Gson gson = new Gson();
    File file = new File("./board.ser2");

    try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      boardList = (List<Board>) in.readObject();

      System.out.printf("%d개의 board 데이터를 로드했습니다.\n", boardList.size());

      in.close();
    } catch (Exception e) {

    }
  }

  private void loadMemberData() {
    File file = new File("./member.ser2");

    try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      memberList = (List<Member>) in.readObject();
      
      System.out.printf("%d개의 member 데이터를 로드했습니다.\n", memberList.size());
      
      in.close();
    } catch (Exception e) {
      
    }
  }

  private void saveBoardData() {
    File file = new File("./board.ser2");

    try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(boardList);
      System.out.printf("%d개의 board 데이터를 저장합니다.\n", boardList.size());

      out.close();
    } catch (Exception e) {

    }
  }

  private void saveMemberData() {
    File file = new File("./member.ser2");
    
    try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(memberList);
      System.out.printf("%d개의 member 데이터를 저장합니다.\n", memberList.size());
      
      out.close();
    } catch (Exception e) {
      
    }
  }
}
