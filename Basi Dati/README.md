## Progetto-OOBD_T1G22
Progetto di Basi di Dati a.a. 2022-2023 Paolo Tedesco Giulio Ruopolo Fabrizio Quaranta N86004408 N86004285 N86004300 Id Gruppo: OOBD_T1G22.
La cartella "SQL" contiene la creazione delle tabelle, procedure/funzioni e l'apposita popolazione.
Per quanto riguarda i diagrammi del progetto, per visualizzarli singolarmente, abbiamo provveduto con l'aggiunta delle cartella"Diagrammi".

La base di dati è stata realizata e testata con l'utilizzo del software "postgresql" versione "6.14".
### Funzionamento Progetto (Windows) :
- Apertura della shell "SQL Shell (psql)";
- Connessione alla base di dati postgres, risiedente sul localhost.
- Creazione del database, tramite il comando : "create database bibliotecaDigitale";
- Accesso alla base di dati con "\c bibliotecaDigitale";
- Esecuzione del file sql contente il modello fisico "\i .../ModelloFisico.sql";
- Esecuzione del file sql contente i vincoli "\i .../Funzioni_Procedure.sql";
- Esecuzione del file sql contente la popolazione del DB "\i .../PopolazioneBD.sql";


#### Definizione del modello fisico, presente in Progetto-OOBD_T1G22/Basi Dati/SQL/ModelloFisico.sql;
#### Definizione delle funzioni, presente in Progetto-OOBD_T1G22/Basi Dati/SQL/Funzioni_Procedure.sql;
#### Popolazione della base di dati, presente in Progetto-OOBD_T1G22/Basi Dati/SQL/PopolazioneBD.sql.
### Spiegazione delle Funzioni / Procedure e i loro Trigger: 

- create function ADD_Disponibile_S_funz()
  - Descrizione :
Funzione che controlla la disponibilita della serie di un libro reso acquistabile
in una piattaforma.
Nel caso in cui non sia segnata come acquistabile la serie nella piattaforma
indicata, ma il numero dei libri di quella serie acquistabili sulla piattaforma
coincide al numero di libri della serie, verra aggiunta la disponibilita della serie
ad essere acquista nella specifica piattaforma.

- create Trigger ADD_Disponibile_S after insert on Disponibile_L
  - Descrizione :
Trigger attivato all’atto dell’inserimento di un libro disponibile in una piatta-
forma, esso manda in esecuzione la funzione ”ADD_Disponibile_S_funz()”;

- create function Controllo_Succ_funz() 
  - Descrizione :
Funzione che all’aggiunta di un libro, appartenete ad una Serie, cerca nella serie
un libro senza il successore ad eccezione del libro stesso, il quale diventera il
predecessore del libro inserito. Di conseguenza saranno eliminate le disponibilita
della serie ad essere acquistate in ogni piattaforma.

- create trigger Controllo_Succ after insert on Libro
  - Descrizione :
Trigger attivato all’atto dell’inserimento di un libro e manda in esecuzione la
funzione Controllo_Succ_funz() per effettuare il possibile collegamento al precedente;

- create function Remove_Disponibile_S_funz() 
  - Descrizione : Funzione che elimina la disponibilita di una serie da una piattaforma 
  nell’eventualita che il libro appartenete alla serie non sia piu disponibile
  sulla stessa piattaforma.

- Create Trigger InDisponibile_S after delete on Disponibile_L
  - Descrizione : Trigger che al momento di una indisponibilita di un libro, manda
in esecuzione Remove_Disponibile_S_funz() per vedere se il libro appartiene a
una serie.

- create procedure ADD_Disponibile_L
  - Descrizione : Funzione che dato in ingresso un libro e una stringa composta
da codici di Acquisti, separati da un ’+’, estrapola dalla stringa gli acquisti e
crea la disponibilita del libro negli Acquisti indicati.

- create procedure ADD_Disponibile_A
  - Descrizione : Funzione che dato in ingresso un Articolo scientifico e una stringa
composta da codici di Acquisti, separati da un ’+’, estrapola dalla stringa gli
acquisti e crea la disponibilita di un Articolo scientifico per gli Acquisti indicati.

- create procedure DEL_Disponibile_L
  - Descrizione : 
Funzione che data in ingresso un libro e una stringa composta
da codici di Acquisti, separati da un ’+’, estrapola dalla stringa gli acquisti e
elimina la disponibilita del libro per gli Acquisti specificati.

- create procedure DEL_Disponibile_A
  - Descrizione : Funzione che data in ingresso un Articolo scientifico e una strin-
ga composta da codici di Acquisti, separati da un ’+’, estrapola dalla stringa
gli acquisti e elimina la disponibilita di un Articolo scientifico dagli Acquisti
indicati.

- create function ADD_view_procedure() returns trigger 
  - Descrizione : Funzione che dato un libro genera le collane riguardanti i suoi
attributi: specificamente gli attributi di genere, autore e editore.

- create trigger ADD_view after insert on libro
  - Descrizione : Trigger che al momento di una inserimento di un libro, manda
in esecuzione ADD_view_procedure(),che genera le collane relative agli attributi
di quel libro.

- create function show_Preferiti()
  - Descrizione : Funzione che data un’ email di un utente mostra i suoi libri
preferiti che sono disponibili indicandone il luogo dove e possibile acquistarli.
