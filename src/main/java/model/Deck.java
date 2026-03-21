package model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Lisää kortin pakkaan
     * @param card lisättävä kortti
     */
    public void addCard(Card card){
        boolean add = cards.add(card);  //metodi , lisää kortin listaan
    }


}
