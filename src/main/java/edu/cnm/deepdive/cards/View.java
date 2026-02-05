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

    Map<Color, List<Card>> result = trick.getResult();
    TrickResult representation = new TrickResult(result.get(Color.BLACK), result.get(Color.RED));

    System.out.println("Count into red pile and black pile:");
    System.out.println(representation);
    System.out.println();

    int numSwaps = trick.swap();

    result = trick.getResult();
    representation = new TrickResult(result.get(Color.BLACK), result.get(Color.RED));

    System.out.println("Swapped " + numSwaps + " cards between our piles:");
    System.out.println(representation);
    System.out.println();

    trick.sortAndAssert();

    result = trick.getResult();
    representation = new TrickResult(result.get(Color.BLACK), result.get(Color.RED));

    System.out.println("Sorted result:");
    System.out.println(representation);
  }

  public record TrickResult(List<Card> blackPile, List<Card> redPile) {
    @Override
    public String toString() {
      long redInRedCount = countPileColor(redPile, Color.RED);
      long blackInBlackCount = countPileColor(blackPile, Color.BLACK);

      return "Red pile (" + redInRedCount + "): " + redPile +
          "\nBlack pile: (" + blackInBlackCount + "): " + blackPile;
    }
  }
}
