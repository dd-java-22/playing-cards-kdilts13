package edu.cnm.deepdive.cards;

import edu.cnm.deepdive.cards.model.Deck;
import java.util.random.RandomGenerator;

public class Main {

  public static void main(String[] args) {
    Deck deck = new Deck();
    System.out.println(deck + "\n");

    System.out.println(deck.size() + "\n");

    System.out.println(deck.deal() + "\n");
    System.out.println(deck.deal() + "\n");
    System.out.println(deck.deal() + "\n");

    System.out.println(deck.size() + "\n");
    System.out.println(deck.isEmpty() + "\n");

    deck.shuffle(RandomGenerator.getDefault());
    System.out.println(deck + "\n");

    deck.sort(null);
    System.out.println(deck);
  }
}
