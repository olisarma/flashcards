# Flashcards

Muistikortti-sovellus, jossa käyttäjä voi luoda pakkoja ja lisätä niihin kortteja.

### Sovelluksessa voi:
-luoda pakkoja
-lisätä kortteja pakkoihin
-muokata kortteja
-poistaa kortteja
-tallentaa tiedot vaikka sovellus suljettaisiin


## Toteutetut toiminnallisuudet
### Pakat
Uuden pakan voi luoda syöttämällä nimen ja painamalla "Lisää"
Pakan voi poistaa "Poista"-painikkeella
Pakan voi avata tuplaklikkaamalla tai painamalla "Avaa"

### Kortit
Kortti koostuu termistä ja selityksestä
Kortteja voi lisätä pakkaan
Kortin voi poistaa
Korttia voi muokata valitsemalla sen listasta ja muuttamalla tietoja

### Tallennus
Kaikki tiedot tallennetaan tiedostoon automaattisesti
Tallennus tapahtuu:
lisäyksen jälkeen
poiston jälkeen
muokkauksen jälkeen

### Validointi
Tyhjiä kenttiä ei voi tallentaa
Puolipiste (;) ei ole sallittu syötteessä
Pakan nimeä ei voi lisätä, jos se on jo olemassa
### Yksikkötestit

Card-luokalle on tehty JUnit-testit
Deck-luokalle on tehty JUnit-testit

## Käyttöohje
1. Pakan luominen:
   Kirjoita pakan nimi tekstikenttään
   Paina "Lisää"
2. Pakan avaaminen:
   Tuplaklikkaa pakkaa TAI
   Valitse pakka ja paina "Avaa"
3. Kortin lisääminen:
   Kirjoita termi ja selitys
   Paina "Lisää kortti" tai Enter
4. Kortin muokkaus:
   Klikkaa korttia listassa
   Muuta tietoja
   Paina "Tallenna muutokset"
5. Kortin poistaminen:
   Valitse kortti
   Paina "Poista kortti"
   Vahvista poisto
6. Pakan arjoittelutila:
   Paina harjoittele
   Aloita selaamaan kortteja, voit myös palata taaksepäin pakassa