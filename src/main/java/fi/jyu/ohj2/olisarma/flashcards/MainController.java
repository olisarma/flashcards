package fi.jyu.ohj2.olisarma.flashcards;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.control.ListView;
import model.Deck;
import javafx.scene.control.TextField;
import model.DeckCollection;

import java.io.IOException;

public class MainController implements Initializable {

    // Yhdistää FXML:n ListView-komponentin tähän muuttujaan pakkaListView (näkyvä käyttöliittymässä)
    @FXML
    private ListView<Deck> pakkaListView;

    @FXML
    private TextField pakkaNameField;

    private DeckCollection deckCollection = new DeckCollection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deckCollection.lataa();
        pakkaListView.setItems(deckCollection.getDecks());
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
        deckCollection.lisaaPakka(uusiPakka);
        deckCollection.tallenna();
        pakkaListView.getSelectionModel().select(uusiPakka);

        pakkaNameField.clear();
    }


    @FXML
    public void handlePoistaPakka() {
        Deck valittuPakka = pakkaListView.getSelectionModel().getSelectedItem();

        // Jos mitään ei ole valittu, lopetetaan
        if (valittuPakka == null) {
            return;
        }

        // Luodaan varmistusdialogi
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Poista pakka");
        alert.setHeaderText("Haluatko varmasti poistaa pakan: " + valittuPakka.getName() + "?");

        Optional<ButtonType> vastaus = alert.showAndWait();
        if (vastaus.get() == ButtonType.OK) {
            deckCollection.poistaPakka(valittuPakka);
            deckCollection.tallenna();
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
