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
import model.DeckCollection;

import java.util.Optional;

public class DeckViewController {

    private Deck deck; // Pakka, jota katsotaan

    private DeckCollection deckCollection; // pakkakokoelma, jonka avulla tiedot tallennetaan tiedostoon

    private Card muokattavaKortti; // pitää muistissa, mikä kortti on muokattavana

    @FXML
    private ListView korttiListView;  // listanäkymä pakan korteille

    @FXML
    private TextField termiField; // tekstikenttä kortin termin syöttöön

    @FXML
    private TextField selitysField; // tekstikenttä selityksen syöttöön


    /**
     *
     * @param deck avattu pakka
     * @param deckCollection kokoelma, johon pakat kuuluvat
     */
    public void setDeckJaCollection(Deck deck, DeckCollection deckCollection) {
        this.deck = deck;
        this.deckCollection = deckCollection;

        korttiListView.setItems(deck.getCards());

        System.out.println("DeckViewController sai pakan: " + deck.getName());

        // Kun käyttäjä valitsee kortin listasta, sen tiedot tuodaan muokattavaksi
        korttiListView.getSelectionModel().selectedItemProperty().addListener((havainto, vanha, uusi) -> {
            if (uusi != null) {
                muokattavaKortti = (Card) uusi;

                // Näytetään valitun kortin tiedot tekstikentissä
                termiField.setText(muokattavaKortti.getTerm());
                selitysField.setText(muokattavaKortti.getExplanation());
            }
        });
    }


    /**
     * Takaisin-napin handlaus. Sulkee nykyisen ikkunan ja palaa takaisin aiempaan näkymään
     * @param event napin painamisesta tullut tapahtuma
     */
    @FXML
    public void handleTakaisin(ActionEvent event){

        // Haetaan nappia vastaava ikkuna (Stage)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Suljetaan ikkuna
        stage.close();
    }

    /**
     * Kun käyttäjä painaa Enteriä termin tekstikentässä, siirretään kursoti selityskenttään.
     * Näin nopeutetaan korttien syöttöä
     * @param event Enter-näppäimen aiheuttama tapahtuma
     */
    @FXML
    public void handleTermiEnter(ActionEvent event) {
        selitysField.requestFocus();
    }

    /**
     * Lisää uuden kortin pakkaan
     * Kortin lisäyksestä tallennetaan myös kaikki tiedot siitä tiedostoon
     * @param actionEvent napin painalluksesta tuleva tapahtuma
     */
    public void handleLisaaKortti(ActionEvent actionEvent) {
        // Validointi (onko tiedot kelvolliset)
        if (!validoiKortti()) {
            return;
        }

        String termi = termiField.getText().trim();
        String selitys = selitysField.getText().trim();

        // Luodaan uusi kortti käyttäjän tekstikenttään kirjoittamsta syötteestä
        Card uusiKortti = new Card(termi, selitys);

        // Lisätään kortti pakkaan
        deck.addCard(uusiKortti);

        // Tallennetaan tiedostoon
        deckCollection.tallenna();

        // Tyhjennetään tekstikentät
        termiField.clear();
        selitysField.clear();

        // Siirretään kursori takaisin termikenttään, jotta saadaan helpommin lisättyä kortteja
        termiField.requestFocus();

        //TODO: Ehkä.. nyt kun painaa jotain korttia niin sen tiedot menee tekstikenttiin,
        // se on vhän ärsyttävää nyt, kun pitää sitten pyyhkiä kaikki jos haluaa lisätä uuden kortin.
    }


    /**
     * Poistaa vlaitun kortin listasta
     * @param event napin painalluksesta tullut tapahtuma
     */
    @FXML
    public void handlePoistaKortti(ActionEvent event) {
        Card valittuKortti = (Card) korttiListView.getSelectionModel().getSelectedItem();

        if (valittuKortti == null) {
            return;
        }

        // Varmistus ikkuna
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Poista kortti");
        alert.setHeaderText("Haluatko varmasti poistaa kortin: " + valittuKortti.getTerm() + "?");

        Optional<ButtonType> vastaus = alert.showAndWait();

        if (vastaus.get() == ButtonType.OK) {
            deck.removeCard(valittuKortti);
            deckCollection.tallenna();
        }
    }

    /**
     * Tallentaa muokattavaan korttiin tehdyt muutokset
     * @param event napin painalluksest tullut tapahtuma
     */
    @FXML
    public void handleTallennaMuutokset(ActionEvent event) {
        if (muokattavaKortti == null) {
            return;
        }

        // Validointi (onko sisältö sallittu)
        if (!validoiKortti()) {
            return;
        }

        String termi = termiField.getText().trim();
        String selitys = selitysField.getText().trim();

        // 1. tallennus card-olioon
        muokattavaKortti.setTerm(termi);
        muokattavaKortti.setExplanation(selitys);

        // 2. tallennus tiedostoon
        deckCollection.tallenna();

        // 3. päivitetään lista näkyviin
        korttiListView.refresh();

        // Tyhjennetään muokkaus"näkymä" ja nollataan muokattavaKortti
        termiField.clear();
        selitysField.clear();
        korttiListView.getSelectionModel().clearSelection();
        muokattavaKortti = null;
        termiField.requestFocus();
    }

    /**
     * Tarkistaa, että kortin tiedot ovat järkevät.
     * Jos kentissä on virheitä, näytetään ne käyttäjälle.
     *
     * @return true jos tiedot ovat kunnossa, muuten false
     */
    private boolean validoiKortti() {

        termiField.setStyle("");
        selitysField.setStyle("");

        String termi = termiField.getText();
        String selitys = selitysField.getText();

        if (termi.contains(";")) {
            termiField.setStyle("-fx-border-color: red;");
            termiField.clear();
            termiField.setPromptText("Merkki ; ei käy!");
            return false;
        }

        if (selitys.contains(";")) {
            selitysField.setStyle("-fx-border-color: red;");
            selitysField.clear();
            selitysField.setPromptText("Merkki ; ei käy!");
            return false;
        }

        if (termi == null || termi.isBlank()) {
            termiField.setStyle("-fx-border-color: red;");
            termiField.setPromptText("Termi puuttuu!");
            return false;
        }

        if (selitys == null || selitys.isBlank()) {
            selitysField.setStyle("-fx-border-color: red;");
            selitysField.setPromptText("Selitys puuttuu!");
            return false;
        }

        return true;
    }

}
