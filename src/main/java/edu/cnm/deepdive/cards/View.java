package edu.cnm.deepdive.cards;

import edu.cnm.deepdive.cards.model.Card;
import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.Suit.Color;
import edu.cnm.deepdive.cards.service.Trick;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

public class View {

  private static final Comparator<Card> BLACK_FIRST_COMPARATOR =
      Comparator.comparing(Card::getColor).thenComparing(Card::compareTo);

  private static final Comparator<Card> RED_FIRST_COMPARATOR =
      Comparator.comparing(Card::getColor, Comparator.reverseOrder()).thenComparing(Comparator.naturalOrder());

  public static int countPileColor(List<Card> pile, Color color) {
    return (int) pile.stream().filter((Card card) -> card.getColor() == color).count();
  }

  public void printStatus(String text, Object o) {
    System.out.println(text);

    if (o != null) {
      System.out.println(o);
    }

    System.out.println();
  }

  public void perform () {
    printStatus("Are you ready for a card trick?", null);

    Deck deck = new Deck();
    printStatus("Here is the deck we are starting with: ", deck);

    RandomGenerator rng = RandomGenerator.getDefault();
    Trick trick = new Trick(deck, rng);

    trick.perform();
    TrickResult representation = new TrickResult(trick.getResult());
    printStatus("Count into red pile and black pile:", representation);

    int numSwaps = trick.swap();
    representation = new TrickResult(trick.getResult());
    printStatus("Swapped " + numSwaps + " cards between our piles:", representation);

    trick.validate();
  }

  public record TrickResult(List<Card> blackPile, List<Card> redPile) {

    public TrickResult(Map<Color, List<Card>> piles) {
      List<Card> sortedBlackPile = piles
          .get(Color.BLACK)
          .stream()
          .sorted(BLACK_FIRST_COMPARATOR)
          .toList();

      List<Card> sortedRedPile = piles
          .get(Color.RED)
          .stream()
          .sorted(RED_FIRST_COMPARATOR)
          .toList();

      this(sortedBlackPile, sortedRedPile);
    }

    @Override
    public String toString() {
      long redInRedCount = countPileColor(redPile, Color.RED);
      long blackInBlackCount = countPileColor(blackPile, Color.BLACK);

      return "Red pile (" + redInRedCount + "): " + redPile +
          "\nBlack pile: (" + blackInBlackCount + "): " + blackPile;
    }
  }
}
