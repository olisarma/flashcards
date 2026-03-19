package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *  Card-luokka kuvaa yhtä muistikorttia.
 *  Jokaisella kortilla on:
 *  - termi (esim. joku englannin kielen sana "dog")
 *  - selitys (esim. käännös "koira")
 */
public class Card {
    // Käytetään StringProperty perus String, sijaan, että voidaan myöhemmin sitoa helposti JavaFX-käyttöliittymään
    private StringProperty term; //dog attribuutti
    private StringProperty explanation;  //koira attribuutti

    /**
     *  Konstruktori(Constructor) luo uuden Card-olion
     *
     * @param term kortin termi (e.g. dog)
     * @param explanation kortin selitys (e.g. koira)
     */
    public Card(String term, String explanation) {
        this.term = new SimpleStringProperty(term);  // uusi olio ja sille konstruktorissa saatu arvo
        this.explanation = new SimpleStringProperty(explanation); // uusi olio ja sille konstruktorissa saatu arvo
    }

    //Term

    /**
     * Palauttaa kortin termin("dog") String-tekstinä
     * @return kortin termi
     */
    public String getTerm() {
        return term.get();  // haetaan uusi (teksti)arvo get()-metodilla
    }

    /**
     * Asettaa kortille uuden termin.
     * Esim. kun kortin sisältöä käyttöliittymässä muokataan (e.g. "dog" -> "cat")
     * @param term uusi termi
     */
    public void setTerm(String term) {
        this.term.set(term);  // asetetaan uusi (teksti)arvo set()-metodilla
    }

    /**
     * Palauttaa term-propertyn
     * Käytetään JavaFX:ssä, kun halutaan sitoa käyttöliittymä suoraan arvoon
     * @return term-property
     */
    public StringProperty termProperty() {
        return term;  // palautetaan property-olio (ei tekstiarvoa) JavaFX-property käyttöliittymää varten
    }

    // Explanation

    /**
     * Palauttaa kortin selityksen "koira" String-tekstinä
     * @return kortin selitys
     */
    public String getExplanation() {
        return explanation.get();
    }

    /**
     * Asettaa kortille uuden selityksen.
     * Käytetään esimerkiksi kun kortin sisältöä muokataan
     * @param explanation uusi selitys
     */
    public void setExplanation(String explanation) {
        this.explanation.set(explanation);
    }

    /**
     * Palauttaa explanation propertyn.
     * Käytetään kun JavaFX:ssä halutaan sitoa käyttöliittymään suoraan arvo
     * @return explanation-property
     */
    public StringProperty explanationProperty() {
        return explanation;
    }

    @Override
    public String toString() {
        return getTerm() + " - " + getExplanation();
    }

}
