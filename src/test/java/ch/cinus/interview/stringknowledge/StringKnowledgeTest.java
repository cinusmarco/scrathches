package ch.cinus.interview.stringknowledge;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ALL")
class StringKnowledgeTest {

    @Test
    void stringEqual() {
        String first = "Marco";
        String second = "Marco";

        System.out.println(first.equals(second));
    }

    @Test
    void stringEqualSign() {
        String first = "Marco";
        String second = "Marco";

        System.out.println(first == second);
    }

    @Test
    void stringEqualSign2() {
        String first = "Marco";
        String second = new String(new char[]{'M','a','r','c','o'});
        String third = second;

        System.err.println("first == second: " + (first == second));
        System.err.println("second == third: " + (second == third));
    }
}
