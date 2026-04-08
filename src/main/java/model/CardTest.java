package model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testiluokka Card-luokalle.
 */
public class CardTest {

    @Test
    public void konstruktoriAsettaaTerminOikein() {
        Card card = new Card("dog", "koira");

        assertEquals("dog", card.getTerm());
    }

    @Test
    public void konstruktoriAsettaaSelityksenOikein() {
        Card card = new Card("dog", "koira");

        assertEquals("koira", card.getExplanation());
    }

    @Test
    public void setTermMuuttaaTermin() {
        Card card = new Card("dog", "koira");

        card.setTerm("cat");

        assertEquals("cat", card.getTerm());
    }

    @Test
    public void setExplanationMuuttaaSelityksen() {
        Card card = new Card("dog", "koira");

        card.setExplanation("kissa");

        assertEquals("kissa", card.getExplanation());
    }

    @Test
    public void toStringPalauttaaTerminJaSelityksen() {
        Card card = new Card("dog", "koira");

        assertEquals("dog - koira", card.toString());
    }
}