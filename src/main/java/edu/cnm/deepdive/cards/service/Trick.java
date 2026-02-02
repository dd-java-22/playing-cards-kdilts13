package edu.cnm.deepdive.cards.service;

import edu.cnm.deepdive.cards.model.Card;
import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.Suit.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.random.RandomGenerator;

public class Trick {

  private final Deck deck;
  private final RandomGenerator rng;

  private final List<Card> blackPile;
  private final List<Card> redPile;

  public Trick(Deck deck, RandomGenerator rng) {
    this.deck = deck;
    this.rng = rng;

    blackPile = new ArrayList<>();
    redPile = new ArrayList<>();
  }

  public void perform(boolean doSwap) {
    deck.shuffle(rng);

    blackPile.clear();
    redPile.clear();

    Card selector;
    Card nextCard;
    while (!deck.isEmpty()) { // use deck size >= 2 instead?
      selector = deck.deal();
      nextCard = deck.deal();

      if (selector.getColor() == Color.BLACK) {
        blackPile.add(nextCard);
      } else {
        redPile.add(nextCard);
      }
    }

    if (doSwap) {
      int maxSwap = Math.min(redPile.size(), blackPile.size());
      int numSwaps = rng.nextInt(maxSwap) + 1;

      for (int i = 0; i < numSwaps; i++) {
        redPile.add(blackPile.removeFirst());
        blackPile.add(redPile.removeFirst());
      }
    }

    blackPile.sort(new BlackFirstComparator());
    redPile.sort(new RedFirstComparator());

    assert countPileColor(blackPile, Color.BLACK) == countPileColor(redPile, Color.RED);
    assert redPile.size() + blackPile.size() == 26;
    assert deck.isEmpty();
  }

  public void reveal() {
    System.out.println(">> redPile " + redPile);
    System.out.println(">> blackPile " + blackPile);

    System.out.println(">> redPile.size() " + redPile.size());
    System.out.println(">> blackPile.size() " + blackPile.size());

    System.out.println(
        ">> countPileColor(redPile, Color.RED) " + countPileColor(redPile, Color.RED));
    System.out.println(
        ">> countPileColor(blackPile, Color.BLACK) " + countPileColor(blackPile, Color.BLACK));
  }

  private static int countPileColor(List<Card> pile, Color color) {
    Iterator<Card> itr = pile.iterator();
    int count = 0;

    while (itr.hasNext()) {
      if (itr.next().getColor() == color) {
        count++;
      }
    }

    return count;
  }

  private static class BlackFirstComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
      int result = card1.getColor().compareTo(card2.getColor());

      if (result == 0) {
        result = card1.compareTo(card2);
      }

      return result;
    }
  }

  private static class RedFirstComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
      int result = -card1.getColor().compareTo(card2.getColor());

      if (result == 0) {
        result = card1.compareTo(card2);
      }

      return result;
    }
  }
}
