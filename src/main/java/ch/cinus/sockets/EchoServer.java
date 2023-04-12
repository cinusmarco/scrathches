/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2023.
 */

package ch.cinus.sockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

  private static final Logger LOGGER = LoggerFactory.getLogger(EchoServer.class);
  private ServerSocket serverSocket;
  private Socket clientSocket;

  private PrintWriter socketChannelOut;
  private BufferedReader socketChannelIn;

  public void start(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    clientSocket = serverSocket.accept();
    socketChannelOut = new PrintWriter(clientSocket.getOutputStream(), true);
    socketChannelIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    String inputLine;
    while ((inputLine = socketChannelIn.readLine()) != null) {
      LOGGER.info("Ricevuto: " + inputLine);
      if (".".equals(inputLine)) {
        socketChannelOut.println("good bye");
        break;
      }
      socketChannelOut.println(inputLine);
    }
  }

  public void stop() throws IOException {
    socketChannelIn.close();
    socketChannelOut.close();
    clientSocket.close();
    serverSocket.close();
  }

  public static void main(String[] args) throws IOException {
    EchoServer server = new EchoServer();
    server.start(4444);
    server.stop();
  }
}
