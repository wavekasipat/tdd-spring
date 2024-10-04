package com.example.day1.testdouble;

import java.util.Random;

public class GenerateNumber {

  private Random random = new Random();

  public GenerateNumber(Random random) {
    this.random = random;
  }

  public int getNumber() {
    int number = random.nextInt(10);
    return number * 2;
  }
}
