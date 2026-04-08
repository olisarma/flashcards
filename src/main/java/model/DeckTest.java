package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testiluokka Deck-luokalle.
 */
public class DeckTest {

    @Test
    public void konstruktoriAsettaaNimenOikein() {
        Deck deck = new Deck("Englanti");

        assertEquals("Englanti", deck.getName());
    }

    @Test
    public void uusiPakkaOnAluksiTyhja() {
        Deck deck = new Deck("Englanti");

        assertTrue(deck.getCards().isEmpty());
    }

    @Test
    public void addCardLisaaKortin() {
        Deck deck = new Deck("Englanti");
        Card card = new Card("dog", "koira");

        deck.addCard(card);

        assertEquals(1, deck.getCards().size());
        assertTrue(deck.getCards().contains(card));
    }

    @Test
    public void removeCardPoistaaKortin() {
        Deck deck = new Deck("Englanti");
        Card card = new Card("dog", "koira");

        deck.addCard(card);
        deck.removeCard(card);

        assertFalse(deck.getCards().contains(card));
    }
}