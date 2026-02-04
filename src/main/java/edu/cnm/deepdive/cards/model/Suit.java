package edu.cnm.deepdive.cards.model;

public enum Suit {
  CLUBS(Color.BLACK) {
    @Override
    public String toString() {
      return "\u2663";
    }
  },
  DIAMONDS(Color.RED) {
    @Override
    public String toString() {
      return "\u2662";
    }
  },
  HEARTS(Color.RED) {
    @Override
    public String toString() {
      return "\u2661";
    }
  },
  SPADES(Color.BLACK) {
    @Override
    public String toString() {
      return "\u2660";
    }
  };

  private final Color color;

  Suit(Color color) {
    this.color = color;
  }

  public Color color() {
    return color;
  }

  public enum Color {
    BLACK, RED
  }
}
