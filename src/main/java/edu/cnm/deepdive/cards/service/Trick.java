package edu.cnm.deepdive.cards.service;

import edu.cnm.deepdive.cards.View;
import edu.cnm.deepdive.cards.model.Card;
import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.Suit.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

  public void perform() {
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
  }

  public void sortAndAssert() {
    blackPile.sort(Comparator.comparing(Card::getColor).thenComparing(Card::compareTo));

    redPile.sort(Comparator.comparing(Card::getColor, Comparator.reverseOrder()).thenComparing(Comparator.naturalOrder()));

    assert View.countPileColor(blackPile, Color.BLACK) == View.countPileColor(redPile, Color.RED);
    assert redPile.size() + blackPile.size() == 26;
    assert deck.isEmpty();
  }

  public int swap() {
    int maxSwap = Math.min(redPile.size(), blackPile.size());
    int numSwaps = rng.nextInt(maxSwap) + 1;

    for (int i = 0; i < numSwaps; i++) {
      redPile.add(blackPile.removeFirst());
      blackPile.add(redPile.removeFirst());
    }

    return numSwaps;
  }

  public Map<Color, List<Card>> getResult() {
   return Map.of(
       Color.BLACK, Collections.unmodifiableList(blackPile),
       Color.RED, Collections.unmodifiableList(redPile)
   );
  }

}
