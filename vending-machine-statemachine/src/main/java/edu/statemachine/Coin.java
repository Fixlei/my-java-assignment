package edu.statemachine;

/**
 * define coin and it's get method.
 */
public class Coin {
  public enum Coins {
    ONE(1), FIVE(5), TEN(10), TWENTY(20), FIFTY(50);

    Coins(int i) {

    }
  }

  private final Coins coin;

  public Coin(Coins coin) {
    this.coin = coin;
  }

  public Coins getValue() {
    return this.coin;
  }
}
