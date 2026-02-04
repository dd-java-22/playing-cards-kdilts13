package edu.cnm.deepdive.cards.model;

public enum Rank {
  ACE,
  TWO,
  THREE,
  FOUR,
  FIVE,
  SIX,
  SEVEN,
  EIGHT,
  NINE,
  TEN,
  JACK,
  QUEEN,
  KING;

  @Override
  public String toString() {
    int value = this.ordinal() + 1;

    return switch (value) {
      case 1 -> "A";
      case 11 -> "J";
      case 12 -> "Q";
      case 13 -> "K";
      default -> "" + value;
    };
  }
}
