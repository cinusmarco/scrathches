package ch.cinus.interview.integerknowledge;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerKnowledgeTest {
    
    @Test
    void integerEqualSignOne() {
        Integer first = 100;
        Integer second = 100;
        assertThat(first == second).isTrue();
    }
 
    @Test
    void integerEqualSignTwo() {
        Integer first = 1000;
        Integer second = 1000;
        assertThat(first == second).isTrue();
    }
}
