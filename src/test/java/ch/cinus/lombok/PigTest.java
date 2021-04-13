/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2021.
 */

package ch.cinus.lombok;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PigTest {

  @Test
  void gettersHaveBeenGenerated() {
    Pig pig = new Pig("Uno", 1);

    assertThat(pig.getName()).isEqualTo("Uno");
  }

  @Test
  void pigHasBuilderPattern() {
    final Pig due = Pig.builder().name("Due").weight(150).build();

    assertThat(due).extracting("name").isEqualTo("Due");
    assertThat(due).extracting("weight").isEqualTo(150);
  }
}
