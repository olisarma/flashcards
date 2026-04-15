package fi.jyu.ohj2.olisarma.flashcards;
import javafx.fxml.FXML;
import model.Card;
import model.Deck;

import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Pakan korttien harjoitustilan toiminta. Pakan kortteja voidaan käydä läpi yksi kerrallaan, kääntää kortin, siirtyä eteen tai taakse päin
 */
public class PracticeController {

    //private Deck deck; //pakka joka on nyt harjoittelunäkymässä
    private List<Card> sekoitetutKortit = new ArrayList<>(); //lista, että kortit saadaan näkymään satunnaisessa järjestyksessä
    private int nykyinenIndeksi = 0; //mikä kortti on nyt näkyvissä
    private boolean selitysNakyvissa = false; //onko selitys näkyvissä

    @FXML
    private Label pakkaNimiLabel;

    @FXML
    private Label termiLabel;

    @FXML
    private Label selitysLabel;

    /**
     * Harjoiteltavan pakan asetus
     * @param deck harjoiteltava pakka
     */
    public void setDeck(Deck deck) {
        //this.deck = deck;

        pakkaNimiLabel.setText("Harjoitellaan pakkaa: " + deck.getName()); //näyttää mikä pakka on harjoittelussa

        sekoitetutKortit = new ArrayList<>(deck.getCards()); // Kopioidaan pakan kortit uuteen listaan, ettei alkuperäistä listaa tarvitse muuttaa
        Collections.shuffle(sekoitetutKortit);

        nykyinenIndeksi = 0; // indeksi pakan läpikäyntiin järjestyksesä
        naytaNykyinenKortti();
    }

    /**
     * Näytetään nykyisen kortin termi, selitys on aluksi piilossa
     */
    private void naytaNykyinenKortti() {
        // Jos ei ole kortteja
        if (sekoitetutKortit == null || sekoitetutKortit.isEmpty()) {
            termiLabel.setText("Pakka on tyhjä");
            selitysLabel.setText("");
            return;
        }

        Card kortti = sekoitetutKortit.get(nykyinenIndeksi);  // haetaan nykyinen kortti

        termiLabel.setText(kortti.getTerm()); // näytetään kortin termi
        selitysLabel.setText("  "); //selitys on piilotettu
        selitysNakyvissa = false;
    }

    /**
     * Kortin kääntö
     * Jos selitys ei näy, näytetään selitys. Jos selitys näkyy, piilotetaan
     */
    @FXML
    public void handleKaannaKortti() {
        // Jos ei ole kortteja
        if (sekoitetutKortit == null || sekoitetutKortit.isEmpty()) {
            return;
        }

        Card kortti = sekoitetutKortit.get(nykyinenIndeksi); // haetaan nykyinen kortti

        //jos selitys ei näkyvissä, näytetään, muuten piilotetaan
        if (!selitysNakyvissa) {
            selitysLabel.setText(kortti.getExplanation());
            selitysNakyvissa = true;
        } else {
            selitysLabel.setText("   ");
            selitysNakyvissa = false;
        }
    }

    /**
     * Seuraavaan korttiin siirtyminen
     * Jos kortti on vika, palataan alkuun
     */
    @FXML
    public void handleSeuraava() {
        if (sekoitetutKortit == null || sekoitetutKortit.isEmpty()) {
            return;
        }

        nykyinenIndeksi++; // siirrytään eteenpäin

        // jos mentiin listan yli, niin aloitetaan alusta
        if (nykyinenIndeksi >= sekoitetutKortit.size()) {
            nykyinenIndeksi = 0;
        }

        naytaNykyinenKortti();
    }

    /**
     * Edelliseen korttiin palaaminen
     * Jos ollaan ensimmäisessä kortissa, siirrytään viimeiseen.
     */
    @FXML
    public void handleEdellinen() {

        //jos kortteja ie ole enää, loppu
        if (sekoitetutKortit == null || sekoitetutKortit.isEmpty()) {
            return;
        }

        nykyinenIndeksi--; //taaksepäin

        //jos käydään ekaa korttia, niin siirrytään vikaan
        if (nykyinenIndeksi < 0) {
            nykyinenIndeksi = sekoitetutKortit.size() - 1;
        }

        naytaNykyinenKortti();
    }
}

