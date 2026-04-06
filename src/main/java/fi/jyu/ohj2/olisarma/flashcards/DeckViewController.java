package fi.jyu.ohj2.olisarma.flashcards;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Card;
import model.Deck;

import java.util.Optional;

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


    @FXML
    public void handlePoistaKortti(ActionEvent event) {
        Card valittuKortti = (Card) korttiListView.getSelectionModel().getSelectedItem();

        if (valittuKortti == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Poista kortti");
        alert.setHeaderText("Haluatko varmasti poistaa kortin: " + valittuKortti.getTerm() + "?");

        Optional<ButtonType> vastaus = alert.showAndWait();
        if (vastaus.get() == ButtonType.OK) {
            deck.removeCard(valittuKortti);
        }
    }

}
