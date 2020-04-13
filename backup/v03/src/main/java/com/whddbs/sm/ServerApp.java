package com.whddbs.sm;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.whddbs.sm.context.Observer;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.json.BoardJsonFileDao;
import com.whddbs.sm.dao.json.MemberJsonFileDao;
import com.whddbs.sm.domain.Board;
import com.whddbs.sm.domain.Member;
import com.whddbs.sm.servlet.BoardAddServlet;
import com.whddbs.sm.servlet.BoardDeleteServlet;
import com.whddbs.sm.servlet.BoardDetailServlet;
import com.whddbs.sm.servlet.BoardListServlet;
import com.whddbs.sm.servlet.MemberAddServlet;
import com.whddbs.sm.servlet.MemberDeleteServlet;
import com.whddbs.sm.servlet.MemberDetailServlet;
import com.whddbs.sm.servlet.MemberListServlet;
import com.whddbs.sm.servlet.Servlet;

public class ServerApp {

  Set<Observer> observers = new HashSet<>(); 
  Map<String, Object> context = new HashMap<>();

  Map<String, Servlet> servletMap = new HashMap<>();

  List<Board> boardList;
  List<Member> memberList;

  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  public void observerInitialized() {
    for (Observer observer : observers) {
      observer.contextInitialized(context);
    }
  }

  public void observerDestroyed() {
    for (Observer observer : observers) {
      observer.contextDestroyed(context);
    } 
  }

  public void service() {
    
    observerInitialized();

    BoardDao boardDao = (BoardDao) context.get("boardDao");
    MemberDao memberDao = (MemberDao) context.get("memberDao");
    
    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));
    servletMap.put("/member/add", new MemberAddServlet(memberDao));
    servletMap.put("/member/list", new MemberListServlet(memberDao));
    servletMap.put("/member/detail", new MemberDetailServlet(memberDao));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberDao));

    try {
      ServerSocket serverSocket = new ServerSocket(7777);
      while (true) {
        Socket socket = serverSocket.accept();

        if (processRequest(socket) == 9) {
          break;
        }
      }

    } catch (Exception e) {
      System.out.println("소켓 접속 오류 : " + e.getMessage());
    }

    observerDestroyed();
  }

  public int processRequest(Socket socket) {
    try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      while (true) {
        String request = in.readUTF();

        switch (request) {
          case "quit":
            quit(out);
            return 9;
        }

        Servlet servlet = servletMap.get(request);

        if (servlet != null) {
          servlet.service(out, in);
        }
      }
    } catch (Exception e) {
      System.out.println("데이터 통신 중 오류 : " + e.getMessage());
      return -1;
    }

  }

  private void quit(ObjectOutputStream out) {
    try {
      out.writeUTF("시스템을 종료합니다.");
      out.flush();  
    } catch (Exception e) {

    }
  }

  public static void main(String[] args) {
    System.out.println("[서버] 접속");

    ServerApp app = new ServerApp();

    app.addObserver(new DataLoaderObserver());

    app.service();

  }
}