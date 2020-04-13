package com.whddbs.sm;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {

  public static void main(String[] args) throws Exception {
    System.out.println("[서버]관리시스템");
    
    ServerSocket serverSocket = new ServerSocket(7777);
    
    while (true) {
      Socket socket = serverSocket.accept();
      
      processRequest(socket);
    }
    
    
  }
  
  private static void processRequest(Socket socket) throws Exception {
    PrintStream out = new PrintStream(socket.getOutputStream());
    Scanner in = new Scanner(socket.getInputStream());
    
    String str = in.nextLine();
    System.out.printf("Client : %s\n",str);
    
    out.println("메시지가 전송되었습니다.");
  }
}
