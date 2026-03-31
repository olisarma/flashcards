# Käyttöliittymän suunnitelma

## Näkymä 1: Päänäkymä, Pakkojen hallinta

![Näkymän karkea ulkoasu kuvana (wireframe.cc, DrawIO, Paint tai paperilla piirretty)](nakyma1.jpg)

**Olennaiset toiminnot**

- Käyttäjä näkee listan pakoista
- Napin uuden pakan lisäämiseen (Uusi pakka)
- Napin pakan poistoon (Valitaan pakka ja painetaan Poista-nappia)
- Napin pakan avaamiseen (Valitaan pakka ja painetaan Avaa-nappia)

- Näkymään pääsee avaamalla sovelluksen

- Käyttäjä voi valita pakan listasta
- Käyttäjä voi lisätä uuden pakan
- Käyttäjä voi poistaa valitun pakan
- Käyttäjä voi avata valitun pakan, että pääsee tarkastelemaan sen kortteja
- Käyttäjä voi muokata pakan nimeä

**Olennaiset komponentit**

- ListView (pakkojen näyttämiseen listana)
- Button (pakan lisääminen, poistaminen ja avaaminen)
- TextField (uuden pakan nimen syöttämiseen)
- Label (tekstien näyttämiseen)
- VBox / HBox (komponenttien asetteluun)
- BorderPane (päänäkymän rakenteeseen)

## Näkymä 2 : Avattu pakka

![Näkymän karkea ulkoasu kuvana (wireframe.cc, DrawIO, Paint tai paperilla piirretty)](nakyma2.jpg)

**Olennaiset toiminnot**

- Käyttäjä näkee avatun pakan nimen ja sen sisältämät kortit
- Näkymään pääsee avaamalla päänäkymästä pakan
- Käyttäjä voi tarkastella pakan kortteja listassa
- Käyttäjä voi lisätä uuden kortin pakkaan (Lisää kortti)
- Käyttäjä voi poistaa koritn pakasta (Poista kortti)
- Käyttäjä voi muokata valitun kortin termiä tai selitystä (Muokkaa)
- Käyttäjä voi siirtyä takaisin päänäkymään (Takaisin)

**Olennaiset komponentit**

- ListView (korttien näyttämiseen listana)
- Label (pakan nimen ja tekstien näyttämiseen)
- Button (kortin lisääminen, muokkaaminen, poistaminen ja takaisin siirtyminen)
- TextField (kortin termin ja selityksen syöttämiseen tai muokkaamiseen)
- VBox / HBox (komponenttien asetteluun)
- BorderPane (näkymän rakenteeseen)
