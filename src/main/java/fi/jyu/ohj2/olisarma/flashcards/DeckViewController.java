package fi.jyu.ohj2.olisarma.flashcards;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Card;
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

    @FXML
    private ListView korttiListView;

    public void setDeck(Deck deck) {
        this.deck = deck;
        korttiListView.setItems(deck.getCards());

        System.out.println("DeckViewController sai pakan: " + deck.getName());
    }


    @FXML
    private TextField termiField;

    @FXML
    private TextField selitysField;

    public void handleLisaaKortti(ActionEvent actionEvent) {
        String termi = termiField.getText().trim();
        String selitys = selitysField.getText().trim();

        if (termi.isEmpty() || selitys.isEmpty()) {
            return;
        }

        Card uusiKortti = new Card(termi, selitys);
        deck.addCard(uusiKortti);

        termiField.clear();
        selitysField.clear();
    }
}
