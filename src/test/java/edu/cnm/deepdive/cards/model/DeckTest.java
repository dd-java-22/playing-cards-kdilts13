package edu.cnm.deepdive.cards.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
//import org.junit.jupiter.api.Assertions;
import java.util.Objects;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeckTest {

  private final int DECK_SIZE = 52;

  Deck deck;
  RandomGenerator rng;

  @BeforeEach
  void prepare_deck() {
    deck = new Deck();
    rng = RandomGenerator.getDefault();
  }

  @Test
  void shuffle() {
    String initialValue = deck.toString();

    deck.shuffle(rng);

    assert(!Objects.equals(deck.toString(), initialValue));
  }

  @Test
  void sort() {
    String initialValue = deck.toString();

    deck.shuffle(rng);

    assert(!Objects.equals(deck.toString(), initialValue));

    deck.sort(null);

    assert(Objects.equals(deck.toString(), initialValue));
  }

  @Test
  void deal_deck_not_empty() {
    Card card = deck.deal();

    assert(card != null);
  }

  @Test
  void deal_deck_empty() {
    while(!deck.isEmpty()) {
      deck.deal();
    }

    assertThrows(NoSuchElementException.class, deck::deal);
  }

  @Test
  void isEmpty() {
    assert (!deck.isEmpty());

    for(int i = 0; i < DECK_SIZE; i++) {
      deck.deal();
    }

    assert (deck.isEmpty());

    deck.sort(null);

    assert (!deck.isEmpty());
  }

  @Test
  void size() {
    assert deck.size() == DECK_SIZE;

    int cardCount = DECK_SIZE;
    while(!deck.isEmpty()) {
      deck.deal();
      cardCount--;

      assert (deck.size() == cardCount);
    }
  }

  @Test
  void testToString() {
    deck.shuffle(rng);

    String value = deck.toString();
    String[] splitString = value.split(",");

    assert(value.startsWith("["));
    assert(value.endsWith("]"));

    assert(splitString.length == DECK_SIZE);

    Card card;
    for (int i = 0; i < DECK_SIZE; i++) {
      card = deck.deal();

      assert(splitString[i].contains(card.toString()));
    }

  }
}