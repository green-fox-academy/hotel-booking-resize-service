package com.greenfox.malachit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CheckIfMain {
  boolean is_main;

  public void setIs_main(boolean is_main) {
    this.is_main = is_main;
  }

  public boolean getIs_main() {
    return this.is_main;
  }
}
