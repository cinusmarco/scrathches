/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2021.
 */

package ch.cinus.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class Pig {

  private String name;
  private int weight;

  public Pig(String name, int weight) {
    this.name = name;
    this.weight = weight;
  }
}
