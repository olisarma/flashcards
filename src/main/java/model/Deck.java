package model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Deck {
    private StringProperty name; // englannin sanoja attribuutti
    private ObservableList<Card> cards; // lista pakan korteista attribuutti

    /**
     * Konstruktori luo uuden pakan.
     * @param name pakan nimi
     */
    public Deck(String name){
        this.name = new SimpleStringProperty(name);
        this.cards = FXCollections.observableArrayList();
    }

    //Metodit

    public String getName(){

        return name.get();

    }

    // Että pakan nimeä voisi muuttaa myöhemmin, mutta ei nyt käytössä
    public void setName(String name){

        this.name.set(name);

    }

    public StringProperty nameProperty() {

        return name;

    }

    //Korttien hallinta

    /**
     * Lisää kortin pakkaan
     * @param card lisättävä kortti
     */
    public void addCard(Card card){

        cards.add(card);

    }

    public void removeCard(Card card){

        cards.remove(card);

    }

    public ObservableList<Card> getCards(){

        return cards;

    }


    @Override
    public String toString() {
        return getName();
    }
}
