package edu.cnm.deepdive.cards.model;

import edu.cnm.deepdive.cards.model.Suit.Color;

public class Card implements Comparable<Card> {
  private final Rank rank;
  private final Suit suit;
  private final String strRepr;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
    this.strRepr = "%s %s".formatted(rank, suit);
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
    int suitCompare = this.suit.compareTo(other.suit);

    if (suitCompare == 0) {
      suitCompare = this.rank.compareTo(other.rank);
    }

    return suitCompare;
  }

  @Override
  public String toString() {
    return strRepr;
  }
}
