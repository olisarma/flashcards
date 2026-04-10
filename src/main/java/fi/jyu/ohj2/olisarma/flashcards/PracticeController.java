package fi.jyu.ohj2.olisarma.flashcards;

import javafx.fxml.FXML;
import model.Card;
import model.Deck;

import java.lang.classfile.Label;
import java.util.ArrayList;
import java.util.List;

public class PracticeController {

    private Deck deck;
    private List<Card> sekoitetutKortit = new ArrayList<>();
    private int nykyinenIndeksi = 0;
    private boolean selitysNakyvissa = false;

    @FXML
    private Label pakkaNimiLabel;

    @FXML
    private Label termiLabel;

    @FXML
    private Label selitysLabel;

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    private void naytaNykyinenKortti() {

    }



}
