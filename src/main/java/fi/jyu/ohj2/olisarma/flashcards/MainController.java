package fi.jyu.ohj2.olisarma.flashcards;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Deck;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainController implements Initializable {

    // Yhdistää FXML:n ListView-komponentin tähän muuttujaan pakkaListView (näkyvä käyttöliittymässä)
    @FXML
    private ListView<Deck> pakkaListView;

    @FXML
    private TextField pakkaNameField;

    // Lista, jossa on kaikki pakat muistissa (näkyvä koodissa) decks
    private ObservableList<Deck> decks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        decks = FXCollections.observableArrayList();  // luodaan tyhjä lista pakoille decks
        pakkaListView.setItems(decks);  // yhdistetään listat
    }

    /**
     * Lisää uuden pakan köyttäjän syöttämällä nimellä.
     */
    @FXML
    public void handleLisaaPakka() {
        String nimi = pakkaNameField.getText().trim();

        if (nimi.isEmpty()) {
            return;
        }

        Deck uusiPakka = new Deck(nimi); //Deck-luokasta
        decks.add(uusiPakka);
        pakkaListView.getSelectionModel().select(uusiPakka);

        pakkaNameField.clear();
    }


    @FXML
    public void handlePoistaPakka() {
        Deck valittuPakka = pakkaListView.getSelectionModel().getSelectedItem();

        if (valittuPakka != null) {
            decks.remove(valittuPakka);
        }
    }


    /*
    Avaa pakan tarkasteluun uuden ikkunan etusivu-ikkunan päälle
     */
    @FXML
    public void handleAvaaPakka(ActionEvent event) {

        Deck valittuPakka = pakkaListView.getSelectionModel().getSelectedItem();

        if (valittuPakka == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fi/jyu/ohj2/olisarma/flashcards/deckview.fxml")
            );
            Parent root = loader.load();

            DeckViewController controller = loader.getController();
            controller.setDeck(valittuPakka);

            Scene scene = new Scene(root);

            Stage tarkastelu = new Stage();
            tarkastelu.setScene(scene);

            tarkastelu.setTitle("Pakan tarkastelu");
            tarkastelu.setMinWidth(500);
            tarkastelu.setMinHeight(400);
            tarkastelu.initModality(Modality.APPLICATION_MODAL);
            tarkastelu.showAndWait();

        }catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
