package edu.cnm.deepdive.cards;

import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.service.Trick;
import edu.cnm.deepdive.cards.service.Trick.TrickResult;
import java.util.random.RandomGenerator;

public class View {
  public void perform () {
    System.out.println("Are you ready for a card trick?");
    System.out.println();

    Deck deck = new Deck();

    System.out.println("Here is the deck we are starting with: ");
    System.out.println(deck);
    System.out.println();

    RandomGenerator rng = RandomGenerator.getDefault();

    Trick trick = new Trick(deck, rng);

    trick.perform();

    System.out.println("Count into red pile and black pile:");
    System.out.println(trick.getResult());
    System.out.println();

    int numSwaps = trick.swap();

    System.out.println("Swapped " + numSwaps + " cards between our piles:");
    System.out.println(trick.getResult());
    System.out.println();

    trick.sortAndAssert();

    System.out.println("Sorted result:");
    System.out.println(trick.getResult());
  }
}
