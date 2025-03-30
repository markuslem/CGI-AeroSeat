# CGI Suvepraktika 2025 Proovitöö
### CGI Summer Internship Test Assignment

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
Istekohtade broneeringute edastamine andmebaasile - 55 min

### Istekohtade soovitamise põhimõtted
* "Next to window" - Kui istekoht on lennuki ääres kohal 'A' või 'D'
* "More legroom" - Kui istekoha ees on vaba ruumi (väljapääsud)
* "Near exit" - Kui istekoht ei ole rohkem kui 2 koha kaugusel väljapääsust
* "Multiple seats" - Kui istekoha kõrval on vähemalt 1 vaba istekoht
* Kui valitakse korraga filtrid "Next to window", "More legroom" ja "Near exit",
siis tõstetakse esile nende istekohtade ühisosa.
* Kui valitud on "Multiple seats", siis tõstetakse esile selle filtri ja ülejäänud filtrite ühend.


Veel teha:
* Tehtud broneeringute vaade