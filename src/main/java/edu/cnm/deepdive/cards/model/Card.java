package edu.cnm.deepdive.cards.model;

import edu.cnm.deepdive.cards.model.Suit.Color;

public class Card implements Comparable<Card> {
  private final Rank rank;
  private final Suit suit;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public Rank getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  public Color getColor() { return suit.color(); }

  @Override
  public int compareTo(Card other) {
//    if (this.getSuit().ordinal() < other.getSuit().ordinal()) {
//      return -1;
//    } else if (this.getSuit().ordinal() > other.getSuit().ordinal()) {
//      return 1;
//    }
//
//    if (this.getRank().ordinal() < other.getRank().ordinal()) {
//      return -1;
//    } else if (this.getRank().ordinal() > other.getRank().ordinal()) {
//      return 1;
//    }
//
//    return 0;

    throw new UnsupportedOperationException("not yet implemented");
  }
}
