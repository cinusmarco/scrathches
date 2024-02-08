package ch.cinus.interview.junit;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MyAfterAllExtension.class)
@ExtendWith(MyAfterEachExtension.class)
@ExtendWith(MyOtherAfterAllExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class ExtensionUnderstandingTest {
  @RegisterExtension
  static MyBeforeAllExtension  myBeforeAllExtension = new MyBeforeAllExtension();

  @RegisterExtension
  static MyOtherAfterAllExtension myOtherAfterAllExtension = new MyOtherAfterAllExtension();

  @RegisterExtension MyBeforeEachExtension myBeforeEachExtension = new MyBeforeEachExtension();

  ExtensionUnderstandingTest() {
    System.err.println("I'm the constructor");
  }

  @Test
  @Order(1)
  void first() {
    System.err.println("I'm the first test");
    assertThat(true).isTrue();
  }

  @Test
  @Order(2)
  void second() {
    System.err.println("I'm the second test");
    assertThat(true).isTrue();
  }
}

class MyBeforeAllExtension implements BeforeAllCallback {

  @Override
  public void beforeAll(ExtensionContext context) {
    System.err.println("MyBeforeAllException::beforeAll");
  }
}

class MyAfterAllExtension implements AfterAllCallback {

  @Override
  public void afterAll(ExtensionContext context) {
    System.err.println("MyAfterAllExtension::afterAll");
  }
}

class MyOtherAfterAllExtension implements AfterAllCallback {

  @Override
  public void afterAll(ExtensionContext context) {
    System.err.println("MyOtherAfterAllExtension::afterAll");
  }
}

class MyBeforeEachExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(ExtensionContext context) {
    System.err.println("MyBeforeEachExtension::beforeEach " + context.getDisplayName());
  }
}

class MyAfterEachExtension implements AfterEachCallback {

  @Override
  public void afterEach(ExtensionContext context) {
    System.err.println("MyAfterEachExtension::afterEach " + context.getDisplayName());
  }
}
