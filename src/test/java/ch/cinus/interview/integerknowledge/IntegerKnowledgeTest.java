package ch.cinus.interview.integerknowledge;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ALL")
public class IntegerKnowledgeTest {

  @Test
  void integerEqualSignOne() {
    Integer first = 100;
    Integer second = 100;
    System.out.println(first == second);
  }

  @Test
  void integerEqualSignTwo() {
    Integer first = 1000;
    Integer second = 1000;
    System.out.println(first == second);
  }
}
