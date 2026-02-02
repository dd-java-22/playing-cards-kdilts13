package edu.cnm.deepdive.cards.model;

import edu.cnm.deepdive.cards.model.Suit.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.random.RandomGenerator;

public class Deck {

  private final List<Card> cards;

  private Iterator<Card> dealer;
  private int remaining;

  public Deck() {
    Suit[] suits = Suit.values();
    Rank[] ranks = Rank.values();

    cards = new ArrayList<>(suits.length * ranks.length);

    for (Suit suit : suits) {
      for (Rank rank: ranks) {
        cards.add(new Card(rank, suit));
      }
    }

    dealer = cards.iterator();
    remaining = cards.size();
  }

  public void shuffle(RandomGenerator rng) {
    Collections.shuffle(cards, rng);
    dealer = cards.iterator();
    remaining = cards.size();
  }

  public void sort(Comparator<Card> comparator) {
    cards.sort(comparator);
    dealer = cards.iterator();
    remaining = cards.size();
  }

  /**
   * @return
   * @throws NoSuchElementException If the deck is empty
   */
  public Card deal() throws NoSuchElementException {
    Card card = dealer.next();
    remaining--;
    return card;
  }

  public boolean isEmpty() {
    return !dealer.hasNext();
  }

  public int size() { return remaining; }

  @Override
  public String toString() {
    return cards.toString();
  }

  public static class Card implements Comparable<Card> {
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
}
