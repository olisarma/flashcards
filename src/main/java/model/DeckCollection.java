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
     * Tallentaa kaikki pakat TODO TÄHÄN? JA NIIDEN KORTIT tiedostoon
     */
    public void tallenna() {
        try (PrintWriter out = new PrintWriter(new FileWriter(tiedosto))) {

            // Käydään kaikki pakat läpi yksitellem
            for (Deck deck : decks) {

                // Tallennetaan ensin pakan nimi omalle rivilleen
                out.println("PAKKA;" + deck.getName());

                // Sitten tallennetaan kaikki tämän pakan kortit
                for (Card card : deck.getCards()) {
                    out.println("KORTTI;" + card.getTerm() + ";" + card.getExplanation());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lukee pakat tiedostosta
     */
    public void lataa() {
        // Tyhjennetään vanha lista, ennen kuin luetaan tiedostosta uudestaam
        decks.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(tiedosto))) {
            String rivi;

            Deck nykyinenPakka = null;

             // Tiedoston luku loop
            while ((rivi = reader.readLine()) != null) {

                // Jaetaan rivi osiin puolipisteen kohdalta
                // esim. "KORTTI;dog;koira" --> ["KORTTI", "dog", "koira"]
                String[] osat = rivi.split(";", 3);

                // Jos uusi pakka alkaa
                if (osat[0].equals("PAKKA")) {
                    nykyinenPakka = new Deck(osat[1]);
                    decks.add(nykyinenPakka);
                }

                // Jos rivi on kortti
                if (osat[0].equals("KORTTI") && nykyinenPakka != null) {
                    String termi = osat[1];
                    String selitys = osat[2];

                    nykyinenPakka.addCard(new Card(termi, selitys));
                }
            }

        } catch (IOException e) {
        }
    }
}