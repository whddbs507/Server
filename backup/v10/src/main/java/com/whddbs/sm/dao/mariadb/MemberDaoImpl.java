package com.whddbs.sm.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.domain.Board;
import com.whddbs.sm.domain.Member;
import com.whddbs.sm.util.ConnectionFactory;

public class MemberDaoImpl implements MemberDao {
  
  ConnectionFactory conFactory;
  
  public MemberDaoImpl(ConnectionFactory conFactory) {
    this.conFactory = conFactory;
  }
  
  public int insert(Member member) throws Exception {
    Connection con = conFactory.getConnection();
    Statement stmt = con.createStatement();
    
    return stmt.executeUpdate("insert into member(no,name,email,pw) values('" + member.getNo()+ "', '" + member.getName() + "', '" + member.getEmail() + "', '" + member.getPw() + "')");
  }
  
  public int delete(int no) throws Exception {
    Connection con = conFactory.getConnection();
    Statement stmt = con.createStatement();
    
    return stmt.executeUpdate("delete from member where no = " + no);
  }

  public List<Member> findAll() throws Exception {
    Connection con = conFactory.getConnection();
    Statement stmt = con.createStatement();
    
    ResultSet rs = stmt.executeQuery("select no,name,email,pw from member");
    
    List<Member> memberList = new ArrayList<>();
    
    while (rs.next()) {
      Member m = new Member();
      
      m.setNo(rs.getInt("no"));
      m.setName(rs.getString("name"));
      m.setEmail(rs.getString("email"));
      m.setPw(rs.getString("pw"));
      
      memberList.add(m);
    }
    
    return memberList;
  }

  public Member findByNo(int no) throws Exception {
    Connection con = conFactory.getConnection();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select no,name,email,pw from member where no = " + no);
    if (rs.next()) {
      Member m = new Member();
      
      m.setNo(rs.getInt("no"));
      m.setName(rs.getString("name"));
      m.setEmail(rs.getString("email"));
      m.setPw(rs.getString("pw"));
      return m;
    }
    return null;
  }
  
  public List<Member> findByKeyword(String keyword) throws Exception {
    Connection con = conFactory.getConnection();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select no, name, email, pw from member where name like '%" + keyword
    + "%' or email like '%" + keyword + "%' or pw like '%" + keyword + "%'");
    
    List<Member> memberList = new ArrayList<>();
    
    while(rs.next()) {
      Member member = new Member();
      
      member.setNo(rs.getInt("no"));
      member.setName(rs.getString("name"));
      member.setEmail(rs.getString("email"));
      member.setPw(rs.getString("pw"));
      member.setPwRe(rs.getString("pw"));
      
      memberList.add(member);
    }
    return memberList;
  }
}