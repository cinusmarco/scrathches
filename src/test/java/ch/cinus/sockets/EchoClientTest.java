/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2023.
 */

package ch.cinus.sockets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class EchoClientTest {

  public static final int SERVER_PORT = 4444;
  public static final String IP = "127.0.0.1";
  private EchoClient client;
  private EchoServer echoServer;

  @BeforeEach
  public void setup() throws IOException, InterruptedException {
    echoServer = new EchoServer();

    Thread serverThread =
        new Thread(
            () -> {
              try {
                echoServer.start(SERVER_PORT);
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            });

    serverThread.start();
    Thread.sleep(1000L);
    client = new EchoClient();
    client.startConnection(IP, SERVER_PORT);
  }

  @AfterEach
  public void tearDown() throws IOException {
    client.stopConnection();
  }

  @Test
  public void givenClient_whenServerEchosMessage_thenCorrect() throws IOException {
    String resp1 = client.sendMessage("hello");
    String resp2 = client.sendMessage("world");
    String resp3 = client.sendMessage("!");
    String resp4 = client.sendMessage(".");

    assertThat(resp1).isEqualTo("hello");
    assertThat(resp2).isEqualTo("world");
    assertThat(resp3).isEqualTo("!");
    assertThat(resp4).isEqualTo("good bye");
  }
}
