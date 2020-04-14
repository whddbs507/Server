package com.whddbs.sm;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.whddbs.sm.context.Observer;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.PhotoBoardDao;
import com.whddbs.sm.servlet.BoardAddServlet;
import com.whddbs.sm.servlet.BoardDeleteServlet;
import com.whddbs.sm.servlet.BoardDetailServlet;
import com.whddbs.sm.servlet.BoardListServlet;
import com.whddbs.sm.servlet.BoardSearchServlet;
import com.whddbs.sm.servlet.MemberAddServlet;
import com.whddbs.sm.servlet.MemberDeleteServlet;
import com.whddbs.sm.servlet.MemberDetailServlet;
import com.whddbs.sm.servlet.MemberListServlet;
import com.whddbs.sm.servlet.MemberSearchServlet;
import com.whddbs.sm.servlet.PhotoBoardAddServlet;
import com.whddbs.sm.servlet.PhotoBoardDeleteServlet;
import com.whddbs.sm.servlet.PhotoBoardDetailServlet;
import com.whddbs.sm.servlet.PhotoBoardListServlet;
import com.whddbs.sm.servlet.PhotoBoardSearchServlet;
import com.whddbs.sm.servlet.Servlet;

public class ServerApp {

  Boolean serverStop = false;
  
  Set<Observer> observers = new HashSet<>(); 
  Map<String, Object> context = new HashMap<>();

  Map<String, Servlet> servletMap = new HashMap<>();

  ExecutorService executorService = Executors.newCachedThreadPool();
  
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
    PhotoBoardDao photoBoardDao = (PhotoBoardDao) context.get("photoBoardDao");
    
    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));
    servletMap.put("/board/search", new BoardSearchServlet(boardDao));
    servletMap.put("/member/add", new MemberAddServlet(memberDao));
    servletMap.put("/member/list", new MemberListServlet(memberDao));
    servletMap.put("/member/detail", new MemberDetailServlet(memberDao));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberDao));
    servletMap.put("/member/search", new MemberSearchServlet(memberDao));
    servletMap.put("/photoboard/add", new PhotoBoardAddServlet(photoBoardDao));
    servletMap.put("/photoboard/list", new PhotoBoardListServlet(photoBoardDao));
    servletMap.put("/photoboard/detail", new PhotoBoardDetailServlet(photoBoardDao));
    servletMap.put("/photoboard/delete", new PhotoBoardDeleteServlet(photoBoardDao));
    servletMap.put("/photoboard/search", new PhotoBoardSearchServlet(photoBoardDao));
    
    try {
      ServerSocket serverSocket = new ServerSocket(7777);
      while (true) {
        Socket socket = serverSocket.accept();

        executorService.submit(() -> {
          processRequest(socket);
        });
        
        if (serverStop) {
          System.out.println("서버가 클라이언트에 의해 종료되었습니다.");
          break;
        }
      }

    } catch (Exception e) {
      System.out.println("소켓 접속 오류 : " + e.getMessage());
    }

    observerDestroyed();
    
    executorService.shutdown();
  }

  public void processRequest(Socket socket) {
    try (PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

        String request = in.nextLine();

        if (request.equalsIgnoreCase("/server/stop")) {
          quit(out);
          return;
        }

        Servlet servlet = servletMap.get(request);

        if (servlet != null) {
          servlet.service(out, in);
        } else {
          
        }
        out.println("!end!");
      
    } catch (Exception e) {
      System.out.println("데이터 통신 중 오류 : " + e.getMessage());
    }

  }

  private void quit(PrintStream out) {
    try {
      serverStop = true;
      out.println("!end!");
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