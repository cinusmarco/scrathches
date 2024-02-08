package ch.cinus.interview.stringknowledge;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringKnowledgeTest {

    @Test
    void stringEqual() {
        String first = "Marco";
        String second = "Marco";

        assertThat(first.equals(second)).isTrue();
    }

    @Test
    void stringEqualSign() {
        String first = "Marco";
        String second = "Marco";

        assertThat(first == second).isTrue();
    }

    @Test
    void stringEqualSign2() {
        String first = "Marco";
        String second = new String(new char[]{'M','a','r','c','o'});
        String third = second;

        assertThat(first == third).isTrue();
    }
}