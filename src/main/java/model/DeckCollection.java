package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * DeckCollection-luokka pakkojen tallentamiseen
 */
public class DeckCollection {

    // Lista, missä kaikki pakat muistissa
    private final ObservableList<Deck> decks = FXCollections.observableArrayList();

    // Tiedosto, johon pakan nimet tallennetaan
    private final String tiedosto = "decks.txt";

    /**
     * Palauttaa kaikki pakat
     *
     * @return lista kaikista pakoista
     */
    public ObservableList<Deck> getDecks() {
        return decks;
    }

    /**
     * Lisää uuden pakan kokoelmaan
     *
     * @param deck lisättävä pakka
     */
    public void lisaaPakka(Deck deck) {
        if (deck == null) {
            return;
        }
        decks.add(deck);
    }

    /**
     * Poistaa pakan kokoelmasta
     *
     * @param deck poistettava pakka
     */
    public void poistaPakka(Deck deck) {
        if (deck == null) {
            return;
        }
        decks.remove(deck);
    }

    /**
     * Tallentaa kaikki pakat tiedostoon
     */
    public void tallenna() {
        try (PrintWriter out = new PrintWriter(new FileWriter(tiedosto))) {
            for (Deck deck : decks) {
                out.println(deck.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lukee pakat tiedostosta
     */
    public void lataa() {
        decks.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(tiedosto))) {
            String rivi;

            while ((rivi = reader.readLine()) != null) {
                decks.add(new Deck(rivi));
            }

        } catch (IOException e) {
        }
    }
}