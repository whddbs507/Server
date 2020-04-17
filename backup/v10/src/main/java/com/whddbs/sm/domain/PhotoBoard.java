package com.whddbs.sm.domain;

import java.sql.Date;

public class PhotoBoard {

  private int no;
  private String title;
  private Date createdDate;
  private String contents;
  private Member member;
  
  public void setMember(Member member) {
    this.member = member;
  }
  
  public Member getMember() {
    return member;
  }
  
  public void setNo(int no) {
    this.no = no;
  }
  
  public int getNo() {
    return no;
  }
  
  public void setcreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  
  public Date getcreatedDate() {
    return createdDate;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setContents(String contents) {
    this.contents = contents;
  }
  
  public String getContents() {
    return contents;
  }
}