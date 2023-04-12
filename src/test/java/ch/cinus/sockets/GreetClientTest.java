/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2023.
 */

package ch.cinus.sockets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class GreetClientTest {

  public static final int SERVER_PORT = 6666;
  private GreetServer greetServer;

  @BeforeEach
  void setup() {
    greetServer = new GreetServer();

    Thread serverThread =
        new Thread(
            () -> {
              try {
                greetServer.start(SERVER_PORT);
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            });

    serverThread.start();
  }

  @Test
  public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect()
      throws IOException, InterruptedException {
    Thread.sleep(1000L);
    GreetClient client = new GreetClient();
    client.startConnection("127.0.0.1", SERVER_PORT);
    String response = client.sendMessage("hello server");

    String expected = "hello client";
    assertThat(response).isEqualTo(expected);
  }
}
