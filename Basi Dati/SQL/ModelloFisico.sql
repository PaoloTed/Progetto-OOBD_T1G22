Create Table Conferenza
(
CodC SERIAL NOT NULL,
Nome VARCHAR(255) NOT NULL,
Struttura VARCHAR(255) NOT NULL,
Indirizzo VARCHAR(255) NOT NULL,
DataI DATE NOT NULL,
DataF DATE NOT NULL,
Responsabile VARCHAR(255) NOT NULL,

CONSTRAINT Conferenza_pk
PRIMARY KEY(CodC),	

CONSTRAINT Controllo_Date_Conferenza
		CHECK(DataI <= DataF)
);


    


Create Table Rivista 
(
Nome VARCHAR(255) NOT NULL,
Data DATE NOT NULL,
Responsabile VARCHAR(255) NOT NULL,
Argomento VARCHAR(255) NOT NULL,

CONSTRAINT Rivista_pk
PRIMARY KEY(Nome, Data)
);

Create Table Articolo_Scientifico
(
DOI  VARCHAR(255) NOT NULL,
Titolo VARCHAR(255) NOT NULL,
Genere VARCHAR(255) NOT NULL,
Numpagine INTEGER NOT NULL,
DataUscita DATE,
Descrizione VARCHAR(1000) NOT NULL,
Fruizione VARCHAR(255) NOT NULL,
Editore VARCHAR(255) NOT NULL,
Autore VARCHAR(255) NOT NULL,
Lingua VARCHAR(255) NOT NULL,
Conferenza INTEGER,
NomeR VARCHAR(255),
DataR DATE,

CONSTRAINT ArticoloScientifico_pk
PRIMARY KEY(DOI),

CONSTRAINT Articolo_conferenza_fk 
FOREIGN KEY(Conferenza) REFERENCES Conferenza(CodC)
			ON DELETE SET NULL ON UPDATE CASCADE,

	CONSTRAINT Articolo_rivista_fk 
FOREIGN KEY(NomeR,DataR) REFERENCES Rivista(Nome,Data)
			ON DELETE SET NULL ON UPDATE CASCADE,

	CONSTRAINT Numero_Pagine_ArticoloS
		CHECK(
(NumPagine > 0 and (Fruizione LIKE '%Cartaceo%' or Fruizione LIKE '%Digitale%'))
or
(NumPagine = 0 and (Fruizione LIKE 'AudioLibro'))
 )
);

Create Table Acquisto
(
CodA SERIAL NOT NULL,
Nome VARCHAR(255),
Tipo VARCHAR(20) NOT NULL,
URL VARCHAR(255),
Indirizzo VARCHAR(255),

CONSTRAINT Acquisto_pk
PRIMARY KEY(CodA),
	CONSTRAINT Fruizione
		CHECK(
((url IS NULL) != (indirizzo IS NULL))AND 
((Tipo = 'Sito web') and (url is not null) or (Tipo = 'Libreria') and (Indirizzo is not null))
)
);

Create Table Presentazione
(
	CodP SERIAL NOT NULL,
	Nome VARCHAR(255) NOT NULL,
	Indirizzo VARCHAR(255) NOT NULL,
	DataPresentazione DATE NOT NULL,
Tipo VARCHAR(255) NOT NULL,

CONSTRAINT Presentazione_pk
	PRIMARY KEY(CodP)
);

	
Create Table Serie
(
CodS SERIAL NOT NULL,
Nome VARCHAR NOT NULL,
NumLibri INTEGER DEFAULT 0,
Completata Boolean DEFAULT FALSE,

CONSTRAINT Serie_pk
PRIMARY KEY(CodS),
	CONSTRAINT Controllo_Completata
		CHECK (( Completata = TRUE AND NumLibri > 1) or Completata = FALSE),
	CONSTRAINT Controllo_NumLibri
		CHECK ( NumLibri >= 0)
);

Create Table Libro
(
	ISBN CHAR(13) NOT NULL,
	Titolo VARCHAR(255) NOT NULL,
	Genere VARCHAR(255),
	NumPagine INTEGER NOT NULL,
	Tipo VARCHAR(255) NOT NULL,
	Materia VARCHAR(255),
	Descrizione VARCHAR(1000) NOT NULL ,
	Fruizione VARCHAR(255) NOT NULL,
	Editore VARCHAR(255) NOT NULL,
	Autore VARCHAR(255) NOT NULL,
	DataUscita DATE,
	Lingua VARCHAR(255) NOT NULL,
	Successivo CHAR(13),
	Serie INTEGER,
	Presentazione INTEGER,

		

	
	
CONSTRAINT Libro_pk
		PRIMARY KEY(ISBN),

CONSTRAINT Libro_fk1
		FOREIGN KEY(Successivo) REFERENCES Libro(ISBN)
			ON DELETE SET NULL ON UPDATE CASCADE,	

	CONSTRAINT Libro_fk2
		FOREIGN KEY(Serie) REFERENCES Serie(CodS)
			ON DELETE CASCADE ON UPDATE CASCADE,	

CONSTRAINT Libro_fk3
		FOREIGN KEY(Presentazione) REFERENCES Presentazione(CodP)
			ON DELETE SET NULL ON UPDATE CASCADE,

	CONSTRAINT Tipo_Libro
		CHECK(
((tipo = 'Didattico') and (Materia is not null)) or ((tipo = 'Romanzo') and (materia is null))
),
CONSTRAINT Numero_Pagine_Libro
		CHECK(
(NumPagine > 0 and (Fruizione LIKE '%Cartaceo%' or Fruizione LIKE '%Digitale%'))
or
(NumPagine = 0 and (Fruizione LIKE 'AudioLibro'))
 )
);


	
	

Create Table Disponibile_L
(
CodA INTEGER NOT NULL,
ISBN CHAR(13) NOT NULL,

CONSTRAINT Disponibile_L_pk
PRIMARY KEY(CodA,ISBN),

CONSTRAINT Disponibile_L_fk1 
FOREIGN KEY(CodA) REFERENCES Acquisto(CodA )
		ON DELETE CASCADE ON UPDATE CASCADE,	

CONSTRAINT Disponibile_L_fk2 
FOREIGN KEY(ISBN) REFERENCES Libro(ISBN)
		ON DELETE CASCADE ON UPDATE CASCADE
);


Create Table Disponibile_S
(
CodA  INTEGER  NOT NULL,
CodS  INTEGER  NOT NULL,

CONSTRAINT Disponibile_S_pk
PRIMARY KEY(CodA,CodS),

 CONSTRAINT Disponibile_S_fk1 
FOREIGN KEY(CodA) REFERENCES Acquisto(CodA)
		ON DELETE CASCADE ON UPDATE CASCADE,	

CONSTRAINT Disponibile_S_fk2 
FOREIGN KEY(CodS) REFERENCES Serie(CodS)
		ON DELETE CASCADE ON UPDATE CASCADE

);


Create Table Disponibile_A
(
	CodA  INTEGER  NOT NULL,
	DOI VARCHAR(255) NOT NULL,

	CONSTRAINT Disponibile_A_pk
PRIMARY KEY(CodA,DOI),

	CONSTRAINT Disponibile_A_fk1 
FOREIGN KEY(CodA) REFERENCES Acquisto(CodA)
		ON DELETE CASCADE ON UPDATE CASCADE,	

 CONSTRAINT Disponibile_A_fk2 
FOREIGN KEY(DOI) REFERENCES Articolo_Scientifico(DOI)
		ON DELETE CASCADE ON UPDATE CASCADE
);
Create Table Utente
(
	Email  VARCHAR(255),
	Password  VARCHAR(255),
	DataIscrizone DATE,

CONSTRAINT Utente_pk
	PRIMARY KEY(Email)
);


Create Table Preferiti
(
Email  VARCHAR(255) NOT NULL,
	CodS  INTEGER  NOT NULL,

CONSTRAINT Preferiti_pk
PRIMARY KEY(Email,CodS),

CONSTRAINT Preferiti_fk1 
FOREIGN KEY(Email) REFERENCES Utente(Email)
		ON DELETE CASCADE ON UPDATE CASCADE,	

CONSTRAINT Preferiti_fk2 
FOREIGN KEY(CodS) REFERENCES Serie(CodS)
		ON DELETE CASCADE ON UPDATE CASCADE
);