package model;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String name; // englannin sanoja attribuutti
    private List<Card> cards = new ArrayList<>();  // lista pakan korteista attribuutti

    public void addCard(Card card){
        boolean add = cards.add(card);  //metodi , lisää kortin listaan
    }
}
