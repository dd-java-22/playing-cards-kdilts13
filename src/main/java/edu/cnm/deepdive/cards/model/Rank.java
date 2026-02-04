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
      case 1 -> "Ace";
      case 11 -> "Jack";
      case 12 -> "Queen";
      case 13 -> "King";
      default -> "" + value;
    };
  }
}
