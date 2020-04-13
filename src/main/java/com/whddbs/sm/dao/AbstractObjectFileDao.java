package com.whddbs.sm.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.whddbs.sm.domain.Board;

public abstract class AbstractObjectFileDao<T> {
  
  protected String filename;
  protected List<T> list;
  
  public AbstractObjectFileDao(String filename) {
    this.filename = filename;
    list = new ArrayList<>();
    loadData();
  }
  
  protected void loadData() {
    File file = new File(filename);
    
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      
      list = (List<T>) in.readObject();
      System.out.printf("총 %d 개의 게시물 데이터를 로딩했습니다.", list.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }
  
  protected void saveData() {
    File file = new File(filename);
    
    try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      
      out.reset();
      out.writeObject(list);
      System.out.printf("총 %d 개의 게시물 데이터를 저장했습니다.\n", list.size());
      
    } catch (Exception e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }
  
  protected abstract <K> int indexOf(K key);
}
