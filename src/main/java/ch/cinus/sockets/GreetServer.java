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

public class GreetServer {

  private static final Logger LOGGER = LoggerFactory.getLogger(GreetServer.class);

  private ServerSocket serverSocket;
  private Socket clientSocket;

  private PrintWriter socketChannelOut;
  private BufferedReader socketChannelIn;

  public void start(int serverPort) throws IOException {
    LOGGER.info("Sono partito");
    serverSocket = new ServerSocket(serverPort);
    LOGGER.info("In attesa di connessioni");
    clientSocket = serverSocket.accept();
    LOGGER.info("Ho accettato la richiesta");

    socketChannelOut = new PrintWriter(clientSocket.getOutputStream(), true);
    socketChannelIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    String greeting = socketChannelIn.readLine();
    if ("hello server".equals(greeting)) {
      socketChannelOut.println("hello client");
    } else {
      socketChannelOut.println("unrecognised greeting");
    }
  }

  public void stop() throws IOException {
    socketChannelIn.close();
    socketChannelOut.close();
    clientSocket.close();
    serverSocket.close();
  }

  public static void main(String[] args) throws IOException {
    GreetServer server = new GreetServer();
    server.start(6666);
  }
}
