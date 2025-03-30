# CGI Suvepraktika 2025 Proovitöö
### CGI Summer Internship Test Assignment

## Ülesseadmine
Täpsustused on application.properties failis (vajadusel võimalik muuta)
* Vajalik Java 21
* Vajalik npm
* Vaja on luua PostgreSQL andebaas nimega aeroseat
* Kasutajanimi: markus
* Salasõna: password
<br>
```cd frontend```\
```npm install```\
```npm run serve```\
<br>
* 
```cd ..```\
```./gradlew build```\
```./gradlew bootRun```

## Ajakulu
projekti esialgne ülesseadmine - 1h\
filtrite lisamine - 1,5h\
PostgreSQL-i ühenduse üles seadmine 2,5h\
Sihtkohtade andmebaasist lugemine - 1,5h\
Andmebaasi täiustamine - 2h\
front-end'is lenndude info kuvamine - 2h 20 min\
Seat tabeli lisamine - 1,5h\
Seat tabeli esitamine läbi SeatControlleri - 30 min\
Istekohtade valiku front-endi loomine - 6h\
Istekohtade broneeringute edastamine andmebaasile - 55 min\
Kujundus - 1h

### Istekohtade soovitamise põhimõtted
* "Next to window" - Kui istekoht on lennuki ääres kohal 'A' või 'D'
* "More legroom" - Kui istekoha ees on vaba ruumi (väljapääsud)
* "Near exit" - Kui istekoht ei ole rohkem kui 2 koha kaugusel väljapääsust
* "Multiple seats" - Kui istekoha kõrval on vähemalt 1 vaba istekoht
* Kui valitakse korraga filtrid "Next to window", "More legroom" ja "Near exit",
siis tõstetakse esile nende istekohtade ühisosa.
* Kui valitud on "Multiple seats", siis tõstetakse esile selle filtri ja ülejäänud filtrite ühend.

### 

Veel teha:
* Kasutaja poolt tehtud broneeringute ajalugu - Tehtud broneeringute istekohtade info ja lennu ID (Flight.id)
võiks salvestada andmebaasi tabelisse.
* Kui lennul broneeritakse istekohad, tuleb veel lisaks vähendada Flight tabelis "occupiedSeats" välja.
Et saavutada parem töökindlus võiks kontrollida veel enne seda, kas kõik istekohad on broneeritud
ühelt lennult.
* Kui kasutaja üritab ühe broneeringuga istekohti hõivata mitmel lennul, siis see tehing peaks katkema. 
See lõhuks ära meie andmebaasi.

## Järeldused
Üllatavalt ajamahukaks osutus front-endi loomine. Back-end-ist võetud info korrektne esitamine
front-end-is osutuks minu jaoks kõige keerulisemaks ülesandeks, kuid tulin sellega ilusti toime.
