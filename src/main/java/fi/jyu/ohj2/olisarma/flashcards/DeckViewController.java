package fi.jyu.ohj2.olisarma.flashcards;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.Deck;

public class DeckViewController {

    @FXML
    public void handleTakaisin(ActionEvent event){

        // Haetaan nappia vastaava ikkuna (Stage)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Suljetaan ikkuna
        stage.close();
    }

    private Deck deck;

    public void setDeck(Deck deck) {
        this.deck = deck;

        System.out.println("DeckViewController sai pakan: " + deck.getName());
    }


}
