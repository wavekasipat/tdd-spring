package com.example.day1.testdouble;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GenerateNumberTest {

  @Test
  @DisplayName("return 14")
  void getNumber() {
    Random stubRandom = new Random() {
      @Override
      public int nextInt(int bound) {
        return 7;
      }
    };

    GenerateNumber generateNumber = new GenerateNumber(stubRandom);
    int result = generateNumber.getNumber();
    assertEquals(14, result);
  }

  @Test
  @DisplayName("spy random 1 time")
  void getNumberSpyRandom() {
    SpyRandom spyRandom = new SpyRandom();
    GenerateNumber generateNumber = new GenerateNumber(spyRandom);
    generateNumber.getNumber();
    assertEquals(1, spyRandom.getCounter());
  }
}

class SpyRandom extends Random {

  int counter = 0;

  @Override
  public int nextInt(int bound) {
    counter++;
    return 7;
  }

  public int getCounter() {
    return counter;
  }
}
