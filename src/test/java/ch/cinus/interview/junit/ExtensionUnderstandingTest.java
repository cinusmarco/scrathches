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

@SuppressWarnings("ALL")
@ExtendWith(MyAfterAllException.class)
@ExtendWith(MyAfterEachException.class)
@TestMethodOrder(OrderAnnotation.class)
class ExtensionUnderstandingTest {
  @RegisterExtension MyBeforeAllException myBeforeAllException = new MyBeforeAllException();

  @RegisterExtension
  static MyOtherAfterAllException myOtherAfterAllException = new MyOtherAfterAllException();

  @RegisterExtension MyBeforeEachException myBeforeEachException = new MyBeforeEachException();

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

class MyBeforeAllException implements BeforeAllCallback {

  @Override
  public void beforeAll(ExtensionContext context) {
    System.err.println("MyBeforeAllException::beforeAll");
  }
}

class MyAfterAllException implements AfterAllCallback {

  @Override
  public void afterAll(ExtensionContext context) {
    System.err.println("MyAfterAllException::afterAll");
  }
}

class MyOtherAfterAllException implements AfterAllCallback {

  @Override
  public void afterAll(ExtensionContext context) {
    System.err.println("MyOtherAfterAllException::afterAll");
  }
}

class MyBeforeEachException implements BeforeEachCallback {

  @Override
  public void beforeEach(ExtensionContext context) {
    System.err.println("MyBeforeEachException::beforeEach " + context.getDisplayName());
  }
}

class MyAfterEachException implements AfterEachCallback {

  @Override
  public void afterEach(ExtensionContext context) {
    System.err.println("MyAfterEachException::afterEach " + context.getDisplayName());
  }
}
