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








/*Funzioni e trigger*/









create function ADD_view_procedure() returns trigger as
$$
declare
	querySqlGenere varchar(1000) := '_collana as select * from libro where genere =';
	querySqlAutore varchar(1000) := '_collana as select * from libro where autore =';
	querySqlEditore varchar(1000) := '_collana as select * from libro where editore =';
begin
	execute 'create or replace view ' || replace(replace(new.genere,' ','_'),'.','_') || querySqlGenere || '''' || new.genere || '''';
	execute 'create or replace view ' || replace(replace(new.autore,' ','_'),'.','_') || querySqlAutore || '''' || new.autore || '''';
	execute 'create or replace view ' || replace(replace(new.editore,' ','_'),'.','_') || querySqlEditore || '''' || new.editore || '''';
return new;
end;
$$
language plpgsql;

create trigger ADD_view  after insert on libro
for each row
	execute function ADD_view_procedure();




create function show_Preferiti(in f_email_in utente.email%type)  

returns table ( NomeSerie varchar(1000),
NomeLuogoAcq varchar(1000)) 
as 
$$
begin
return query select se.nome,ac.Nome
from preferiti as pr
join serie as se on pr.codS = se.codS 
join disponibile_S as ds on se.codS = ds.codS 
join acquisto as ac on ds.codA = ac.codA
where pr.email = f_email_in;
end;
$$
language plpgsql;

create function ADD_Disponibile_S_funz() returns trigger as
$$
declare
	f_codSerie libro.serie%type;
	f_numLibriS serie.numLibri%type;
f_codSerieApp libro.serie%type;
f_numLibriAcq serie.numLibri%type;

begin
	select serie into f_codSerie
	from libro 
where isbn = new.isbn;

select numLibri  into  f_numLibriS
from serie 
where codS = f_codSerie;

	select codS into f_codSerieApp       /*Controlliamo se l'intera serie è già tutta disponibile*/
	from disponibile_S
	where codS = f_codSerie and codA = new.codA;

	if f_codSerieApp is null then 
		select count(*) into f_numLibriAcq
		from disponibile_L
		where codA = new.codA and isbn in (select isbn from libro where serie = f_codSerie);

		if f_numLibriAcq = f_numLibriS then  /*Controllo sulla quantità libri disponibili*/
			insert into disponibile_S(CodA,CodS) values (new.codA, f_codSerie);
		end if;
	end if;
return new;
end;
$$   
language plpgsql;

Create Trigger ADD_Disponibile_S after insert on Disponibile_L
for each row
	execute function ADD_Disponibile_S_funz();
	
	
	
	
create function Controllo_Succ_funz() returns trigger as
$$
declare
f_codSerie libro.serie%type;
f_libro_noSucc libro.isbn%type;
f_numLibri serie.NumLibri%type;
begin
	f_codSerie := new.serie;
	select isbn into f_libro_noSucc
	from libro where successivo is null and serie = f_codSerie and isbn <> new.isbn;
 	
	if new.Serie is not null then 
		delete from disponibile_S  /*sapendo di aggiungere un nuovo libro andremo a togliere */		 	where codS =  new.Serie;    /*i vecchi disponibile _S dato che la serie è incrementata*/
	select numLibri into f_numLibri    /*Andiamo a prender la quantita di libri già uscita */
	from serie
	where codS = new.Serie;

f_numLibri := f_numLibri +1;
	update serie set numLibri = f_numLibri  /*Andiamo ad incrementare il numero di libri  */
	where codS = new.Serie;

	if f_libro_noSucc is not null then 
		update libro set successivo = new.isbn   /*Andiamo a inserire l'isbn nel predecessore*/
		where isbn = f_libro_noSucc;
	end if;
	end if;
return new;
end;
$$
language plpgsql;

create trigger Controllo_Succ after insert on Libro 
for each row
	execute function Controllo_Succ_funz();



create function Remove_Disponibile_S_funz() returns trigger as
$$
declare
	f_codSerie libro.serie%type; 	
begin
	select serie into f_codSerie
	from libro 
	where isbn = old.isbn;
	if f_codSerie is not null then 
		Delete from Disponibile_S
		Where codA = old.codA AND codS = f_codSerie;			
	end if;
return new;
end;
$$
language plpgsql;

Create Trigger InDisponibile_S after delete on Disponibile_L
for each row
	execute function Remove_Disponibile_S_funz();


create procedure ADD_Disponibile_L(in f_ISBN_in libro.ISBN%type, in f_acqStrng_in varchar(255)) as
$$
declare   /*f_acqStrng_in e una stringa che contine più codA*/
	f_acqStrngAppo VARCHAR(255);
	f_pos INTEGER;
	f_codACntrl disponibile_l.codA%type;
	f_codA disponibile_l.codA%type;
	f_acqStrngAppoINT INTEGER;
	
begin
	f_acqStrngAppo := f_acqStrng_in;
	while f_acqStrngAppo is not null loop
		
		f_pos := position('+' in f_acqStrngAppo);
		if f_pos <> 0 then 
		
			f_codA := cast(substring(f_acqStrngAppo, 1, f_pos - 1) as INTEGER);
			f_acqStrngAppo := replace(f_acqStrngAppo, concat(f_codA,'+'),'');
			
			select codA into f_codACntrl
			from disponibile_L 
			where ISBN = f_ISBN_in and codA = f_codA;

			if f_codACntrl is null then 
				insert into disponibile_L(codA,ISBN) values 
				(f_codA,f_ISBN_in);
			end if;
		else
			
			f_acqStrngAppoINT := cast(f_acqStrngAppo as INTEGER);
			
			select codA into f_codACntrl
			from disponibile_L 
			where ISBN = f_ISBN_in and codA = f_acqStrngAppoINT;
			
			if f_codACntrl is null then 
				insert into disponibile_L(codA,ISBN) values 
				(f_acqStrngAppoINT,f_ISBN_in);
			end if;
			f_acqStrngAppo := NULL;
		end if;
	end loop;
end;
$$
language plpgsql;



create procedure ADD_Disponibile_A(in f_DOI_in  Articolo_Scientifico.DOI%type, in f_acqStrng_in varchar(255)) as
$$
declare
	f_acqStrngAppo VARCHAR(255);
	f_pos INTEGER;
	f_codACntrl disponibile_l.codA%type;
	f_codA disponibile_l.codA%type;
	f_acqStrngAppoINT INTEGER;
	
begin
	f_acqStrngAppo := f_acqStrng_in;
	while f_acqStrngAppo is not null loop
		
		f_pos := position('+' in f_acqStrngAppo);
		if f_pos <> 0 then 
		
			f_codA := cast(substring(f_acqStrngAppo, 1, f_pos - 1) as INTEGER);
			f_acqStrngAppo := replace(f_acqStrngAppo, concat(f_codA,'+'),'');
			
			select codA into f_codACntrl
			from disponibile_A 
			where DOI = f_DOI_in and codA = f_codA;

			if f_codACntrl is null then 
				insert into disponibile_A(codA,DOI) values 
				(f_codA,f_DOI_in);
			end if;
		else
			
			f_acqStrngAppoINT := cast(f_acqStrngAppo as INTEGER);
			
			select codA into f_codACntrl
			from disponibile_A 
			where DOI = f_DOI_in and codA = f_acqStrngAppoINT;
			
			if f_codACntrl is null then 
				insert into disponibile_A(codA,DOI) values 
				(f_acqStrngAppoINT,f_DOI_in);
			end if;
			f_acqStrngAppo := NULL;
		end if;
	end loop;
end;
$$
language plpgsql;



create procedure DEL_Disponibile_L(in f_ISBN_in libro.ISBN%type, in f_acqStrng_in varchar(255)) as
$$
declare
	f_acqStrngAppo VARCHAR(255);
	f_pos INTEGER;
	f_codACntrl disponibile_l.codA%type;
	f_codA disponibile_l.codA%type;
	f_acqStrngAppoINT INTEGER;
	
begin
	f_acqStrngAppo := f_acqStrng_in;
	while f_acqStrngAppo is not null loop
		
		f_pos := position('+' in f_acqStrngAppo);
		if f_pos <> 0 then 
		
			f_codA := cast(substring(f_acqStrngAppo, 1, f_pos - 1) as INTEGER);
			f_acqStrngAppo := replace(f_acqStrngAppo, concat(f_codA,'+'),'');
			
			select codA into f_codACntrl
			from disponibile_L 
			where ISBN = f_ISBN_in and codA = f_codA;

			if f_codACntrl is not null then 
				Delete from disponibile_L
				Where (CodA=f_codA AND  ISBN=f_ISBN_in);
			end if;
		else
			
			f_acqStrngAppoINT := cast(f_acqStrngAppo as INTEGER);
			
			select codA into f_codACntrl
			from disponibile_L 
			where ISBN = f_ISBN_in and codA = f_acqStrngAppoINT;
			
			if f_codACntrl is not null then 
				Delete from disponibile_L
				Where (CodA=f_acqStrngAppoINT AND  ISBN=f_ISBN_in);
			end if;
			f_acqStrngAppo := NULL;
		end if;
	end loop;
end;
$$
language plpgsql;


create procedure DEL_Disponibile_A(in f_DOI_in articolo_scientifico.DOI%type, in f_acqStrng_in varchar(255)) as
$$
declare
	f_acqStrngAppo VARCHAR(255);
	f_pos INTEGER;
	f_codACntrl disponibile_A.codA%type;
	f_codA disponibile_A.codA%type;
	f_acqStrngAppoINT INTEGER;
	
begin
	f_acqStrngAppo := f_acqStrng_in;
	while f_acqStrngAppo is not null loop
		
		f_pos := position('+' in f_acqStrngAppo);
		if f_pos <> 0 then 
		
			f_codA := cast(substring(f_acqStrngAppo, 1, f_pos - 1) as INTEGER);
			f_acqStrngAppo := replace(f_acqStrngAppo, concat(f_codA,'+'),'');
			
			select codA into f_codACntrl
			from disponibile_A
			where DOI = f_DOI_in and codA = f_codA;

			if f_codACntrl is not null then 
				Delete from disponibile_A
				Where (CodA=f_codA AND  DOI=f_DOI_in);
			end if;
		else
			
			f_acqStrngAppoINT := cast(f_acqStrngAppo as INTEGER);
			
			select codA into f_codACntrl
			from disponibile_A
			where DOI = f_DOI_in and codA = f_acqStrngAppoINT;
			
			if f_codACntrl is not null then 
				Delete from disponibile_A
				Where (CodA=f_acqStrngAppoINT AND  DOI=f_DOI_in);
			end if;
			f_acqStrngAppo := NULL;
		end if;
	end loop;
end;
$$
language plpgsql;



/*popolazione*/


INSERT INTO Presentazione (Nome, Indirizzo, DataPresentazione, Tipo)
VALUES ('Il primo Libro di Cicciogamer', 'Via Luca Giordano, 5, 00197 Roma','16-6-2020','Libreria');

INSERT INTO Presentazione (Nome, Indirizzo, DataPresentazione, Tipo)
VALUES ('Il primo Libro del trono di spade','Via tre spade, 3, 33333 Genova', '31-10-2013','Sala');

INSERT INTO Presentazione (Nome, Indirizzo, DataPresentazione, Tipo)
VALUES ('Il primo libro di harry potter','Via Pavone, 100, 72891 Torino', '26-11-2020','Libreria');

INSERT INTO Presentazione (Nome, Indirizzo, DataPresentazione, Tipo)
VALUES ('Il primo libro del diario di una schiappa','Via Messina, 30, 29471 Fiumicino', '2008-02-08','Sala');

INSERT INTO Presentazione (Nome, Indirizzo, DataPresentazione, Tipo)
VALUES ('Il secondo libro del diario di una schiappa','Via Beethoven, 0, 289503 Caltanissetta', '2014-01-10','Libreria');

INSERT INTO Presentazione (Nome, Indirizzo, DataPresentazione, Tipo)
VALUES ('Il terzo libro del diario di una schiappa','Via Luca Giordani, 10, 19700 Lazio', '2018-06-10','Libreria');

INSERT INTO Presentazione (Nome, Indirizzo, DataPresentazione, Tipo)
VALUES ('Il quarto libro di harry potter','Via Joao Felix, 9, 48290 Milano', '2021-01-23','Sala');

INSERT INTO Presentazione (Nome, Indirizzo, DataPresentazione, Tipo)
VALUES ('Il libro : In cucina con ciccio','Via Ochoa, 1, 84121 Salerno', '15-6-2020','Libreria');

INSERT INTO Presentazione (Nome, Indirizzo, DataPresentazione, Tipo)
VALUES ('Il Quarto libro del trono di spade','Via Mario Rui, 8, 88888 Napoli', '18-5-2020','Sala');


INSERT INTO SERIE (Nome, NumLibri, Completata)
VALUES ('Harry Potter',0,FALSE);

INSERT INTO Libro (ISBN, Titolo, Genere, NumPagine, Tipo, Materia, Descrizione, Fruizione, Editore, Autore, DataUscita, Lingua, Successivo, Serie, Presentazione)
VALUES
('9788831004169',' Harry Potter e la pietra filosofale',' Fantasy',368,'Romanzo',NULL,'Il primo libro della saga di Harry Potter, riccamente illustrato a colori e con otto inediti elementi interattivi, tra cui la vera lettera di Hogwarts da aprire! Questa edizione vi farà vivere la storia di Harry Potter e la Pietra Filosofale come mai 
prima d ora: la versione integrale del romanzo di J.K. Rowling è accompagnata in ogni pagina da splendide illustrazioni a colori e da otto elementi interattivi. Il lettore potrà aprire la lettera di Hogwarts come se fosse Harry, scoprire cosa si cela oltre il magico ingresso di Diagon Alley, far comparire un sontuoso banchetto nella Sala Grande e tanto altro.','Cartaceo,Digitale,AudioLibro ','Salani ','J.K. Rowling ','2020-11-26','Italiano',NULL,1,3);

INSERT INTO Libro VALUES
('9788831003391', 'Harry Potter e la Camera dei Segreti','Fantasy',224,'Romanzo',NULL,'«C è un complotto, Harry Potter. Un complotto per far succedere le cose più terribili, quest anno, alla scuola di magia e stregoneria di Hogwarts». A Hogwarts il nuovo anno scolastico s inaugura all insegna degli enigmi: strane voci riecheggiano nei corridoi, Ginny, la sorella di Ron, sparisce nel nulla e un incantesimo colpisce i compagni di Harry, uno dopo l altro, e sembra legato a un antico mistero racchiuso nella tenebrosa camera dei segreti... Il secondo capitolo di uno dei più grandi fenomeni letterari internazionali. Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosità sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro','Salani ','J.K. Rowling ','2020-12-23','Italiano',NULL,1,NULL);
INSERT INTO Libro VALUES
('9788831003407','Harry Potter e il prigioniero di Azkaban',' Fantasy',416,'Romanzo',NULL,'«Benvenuti sul Nottetempo, mezzo di trasporto di emergenza per maghi e streghe in difficoltà. Allungate la bacchetta, salite a bordo e vi portiamo dove volete». Una terribile minaccia incombe sulla scuola di magia e stregoneria di Hogwarts. Sirius Black, il famigerato assassino, è evaso dalla prigione di Azkaban. È a caccia e la sua preda è proprio a Hogwarts, dove Harry e i suoi amici stanno per cominciare il loro terzo anno. Nonostante la sorveglianza dei Dissennatori la scuola non è più un luogo sicuro, perché al suo interno si nasconde un traditore... Il terzo capitolo di uno dei più grandi fenomeni letterari internazionali. Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosità sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro ','Salani ','J.K. Rowling ','2020-12-29','Italiano',NULL,1,7);

INSERT INTO Libro VALUES
('9788831003414',' Harry Potter e il calice di fuoco',' Fantasy',672,'Romanzo',NULL,'«Le sfide saranno tre, distribuite nell arco dell anno scolastico, e metteranno alla prova i campioni in molti modi diversi ... La loro perizia magica, la loro audacia, i loro poteri deduttivi e, naturalmente, la loro capacità di affrontare il pericolo». Questo che avete tra le mani è il volume centrale delle avventure di Harry Potter. Ormai Harry è un mago adolescente, vuole andarsene dalla casa dei Dursley, vuole sognare la Cercatrice di Corvonero per cui ha una cotta tremenda... E poi vuole scoprire di più sulla grande competizione che si terrà a Hogwarts e non si svolge da cento anni. Harry vuole davvero essere un normale mago di quattordici anni. Ma sfortunatamente non è normale, nemmeno come mago. E stavolta la differenza può essere fatale. Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosità sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro ','Salani ','J.K. Rowling ','2021-01-13','Italiano',NULL,1,NULL);
INSERT INTO Libro VALUES
('9788831003421','Harry Potter e l''ordine della Fenice','Fantasy',880,'Romanzo',NULL,'«Tu condividi i suoi pensieri e le sue emozioni. Il preside ritiene che questo non debba continuare. Desidera che io ti insegni a chiudere la mente al Signore Oscuro». Il quinto anno a Hogwarts si annuncia carico di sfide difficili per Harry Potter: Lord Voldemort è tornato. Che cosa succederà ora che il Signore Oscuro è di nuovo in pieno possesso dei suoi terrificanti poteri? Il Ministro della Magia sembra non prendere sul serio questa spaventosa minaccia. Toccherà a Harry organizzare la resistenza, con l aiuto degli amici di sempre e il tumultuoso coraggio dell adolescenza. Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosità sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro ','Salani ','J.K. Rowling ','2021-01-18','Italiano',NULL,1,NULL);

INSERT INTO Libro VALUES
('9788831003438',' Harry Potter e il principe mezzosangue',' Fantasy',592,'Romanzo',NULL,'«Sospeso nel buio sopra la scuola c era il vivido teschio verde con la lingua di serpe, il marchio lasciato dai Mangiamorte tutte le volte che entravano in un edificio... Tutte le volte che uccidevano...». È il sesto anno a Hogwarts e per Harry niente è più come prima. L ultimo legame con la sua famiglia è troncato, perfino la scuola non è la dimora accogliente di un tempo. Voldemort ha radunato le sue forze e nessuno può più negare il suo ritorno. Harry capisce che è arrivato il momento di affrontare il suo destino. L ultimo atto si avvicina, sarà all altezza di questa sfida fatale? Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosità sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro ','Salani ','J.K. Rowling ','2021-01-25','Italiano',NULL,1,NULL);
INSERT INTO Libro VALUES
('9788831003445','Harry Potter e i Doni della Morte',' Fantasy',688,'Romanzo',NULL,'«Consegnatemi Harry Potter» proseguì la voce di Voldemort, «e a nessuno verrà fatto del male. Consegnatemi Harry Potter e lascerò la scuola intatta. Consegnatemi Harry Potter e verrete ricompensati». Il confronto finale con Voldemort è imminente, una grande battaglia è alle porte e Harry, con coraggio, compirà ciò che dev essere fatto. Mai i perché sono stati così tanti e mai come in questo libro si ha la soddisfazione delle risposte. Giunti all ultima pagina si vorrà rileggere tutto daccapo, per chiudere il cerchio, per riscoprire tutti i segreti e i significati profondi, ma soprattutto per ritardare il più possibile il distacco dai meravigliosi personaggi che ci hanno accompagnato per così tanto tempo e che non hanno ancora smesso di incantarci. Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosità sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro ','Salani ','J.K. Rowling ','2021-02-03','Italiano',NULL,1,NULL);
INSERT INTO Libro VALUES
('9788893818407',' Harry Potter e la maledizione dell erede',' Fantasy',384,'Romanzo',NULL,'L ottava storia. Diciannove anni dopo. È sempre stato difficile essere Harry Potter e non è molto più facile ora, da impiegato al Ministero della Magia, oberato di lavoro, marito e padre di tre figli in età scolare. Mentre Harry Potter fa i conti con un passato che si rifiuta di rimanere tale, Albus, suo secondogenito, deve lottare con il peso di un eredità familiare che non ha mai voluto. Quando passato e presente si fondono in un oscura minaccia, padre e figlio apprendono una scomoda verità: il pericolo proviene a volte da luoghi inaspettati. Basato su una storia originale di J.K. Rowling, John Tiffany e Jack Thorne, lo script di Harry Potter e la Maledizione dell Erede è stato pubblicato come edizione speciale in occasione della première che si è tenuta nel West End di Londra nell estate del 2016. Lo spettacolo ha ricevuto un accoglienza entusiastica da pubblico e critica e il libro è immediatamente diventato un bestseller internazionale. ','Cartaceo,Digitale,AudioLibro ','Salani ','J.K. Rowling ','2021-02-22','Italiano',NULL,1,NULL);

UPDATE Serie set completata = TRUE  where nome = 'Harry Potter';


INSERT INTO SERIE(Nome, NumLibri, Completata)
VALUES ('Diario di una Schiappa',0,'False');

INSERT INTO Libro VALUES
('9788880334392','Diario di una schiappa 1','Commedia',217,'Romanzo',NULL,'Essere un ragazzo è un mestiere complicato. Nessuno lo sa meglio di Greg, che ha iniziato la scuola media e si ritrova in mezzo a compagni ben più alti di lui, ragazze improvvisamente grandi, e amici con cui è così difficile andare d''accordo. Diario di una schiappa è la cronaca delle avventure quotidiane di un imprevedibile e simpaticissimo antieroe','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2008-02-05','Italiano',NULL,'2',4);

INSERT INTO Libro VALUES
('9788803348422','Diario di una schiappa 2. La legge dei più grandi','Commedia',216,'Romanzo',NULL,'Non chiedete a Greg come sono andate le vacanze. Decisamente preferisce non parlarne e poi c''è stato un episodio davvero imbarazzante che nessuno dovrebbe scoprire. Peccato che suo fratello Rodrick conosca tutti i dettagli e glielo ricordi in continuazione. Tra vecchi e nuovi amici, scherzi tremendi a scuola e segreti che vengono scoperti, essere un ragazzo è un mestiere sempre più complicato.','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2009-02-03','Italiano',NULL,2,5);

INSERT INTO Libro VALUES
('9788880335214','Diario di una schiappa 3. Ora basta!','Commedia',217,'Romanzo',NULL,'Non c è niente da fare: Greg rimarrà sempre una schiappa e a lui tutto sommato sta bene. Qualcuno però dovrebbe spiegarlo a suo padre Frank, che vuole iscriverlo a sport di squadra e ad altre attività che ne facciano un vero uomo. Ora pensa addirittura all Accademia Militare! Come farà Greg a schivare i deliranti propositi di suo padre, a conquistare la ragazza che gli piace e ad arrivare sano e salvo alle vacanze estive? Greg capisce che questa volta è alla resa dei conti, fa di tutto per salvarsi, e noi come sempre facciamo il tifo per lui. Ormai Diario di una schiappa è un must per i ragazzi, che racconta la loro vita quotidiana con humour, intelligenza e leggerezza. ','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2009-02-03','Italiano',NULL,2,6);

INSERT INTO Libro VALUES
('9788880335719','Diario di una schiappa 4. Vita da cani','Commedia',218,'Romanzo',NULL,'Sono arrivate le vacanze estive, il tempo è fantastico e tutti i ragazzi vivono all aria aperta. Ma cosa fa Greg? Chiuso in casa, con i videogiochi e le tende tirate, sta realizzando il suo sogno: un periodo senza obblighi e responsabilità. Sua madre però ha un idea tutta diversa dell''estate perfetta... un idea fatta di attività all''aperto e gite con la famiglia. Chi avrà la meglio?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2011-02-01','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788880336181','Diario di una schiappa 5. La dura verità' ,'Commedia',218,'Romanzo',NULL,'Greg ha sempre avuto una gran fretta di crescere. Ma diventare grandi è davvero uno spasso come ci si aspetta? Fra riunioni di famiglia, lezioni sui fatti della vita, una piccola guerra con la nuova colf e un apocalittica notte trascorsa a scuola, Greg dovrà affrontare nuove ed esilaranti avventure senza il fedele amico Rowley. Ce la farà da solo?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2012-02-01','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788880336655','Diario di una schiappa 6. Si salvi chi può','Commedia',218,'Romanzo',NULL,'Greg Heffley è nei guai. Qualcuno ha sporcato i muri della scuola e lui è il primo sospettato. Ma Greg è sicuro di essere innocente! O almeno, quasi innocente... Quando la verità sta per essere scoperta, una tempesta di neve si abbatte sulla città e la famiglia Heffley rimane bloccata in casa. Greg sa che allo sciogliersi della neve dovrà affrontare le autorità, ma nel frattempo... c''è una punizione peggiore del passare le vacanze in trappola con Manny, Rodrick e Mamma?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2013-01-03','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788880337775','Diario di una schiappa 7. Guai in arrivo!','Commedia',218,'Romanzo',NULL,'Povero Greg, anche questa volta sono guai, ma guai seri! La scuola ha organizzato una grande festa, e se non vuole essere tagliato fuori, deve affrontare un''impresa quasi impossibile. Imparare a ballare? Sì, ma non solo: Greg deve procurarsi una ragazza. Fra i consigli dello zio Gary, un radicale cambio di look e una serie di fiaschi colossali, le cose sembrano mettersi male. Per fortuna che c''è Rowley! Con il suo aiuto, Greg trova il modo di svoltare la serata, ma... siamo sicuri che non ci siano altri guai in arrivo?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2014-01-03','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788880338949','Diario di una schiappa 8. Sfortuna nera.','Commedia',217,'Romanzo',NULL,'Per Greg è un momento difficile. Il suo migliore amico Rowley passa le giornate con la fidanzata e non ha più tempo per lui. E trovarsi dei nuovi amici alla scuola media è una vera impresa. Per migliorare la sua sorte, Greg decide di affidarsi alla Palla magica. Basta fare una domanda e la palla fornisce tutte le risposte. Evviva! All inizio sembra funzionare a meraviglia, ma la Palla magica riuscirà a cambiare davvero le cose?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2014-11-13','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869660337','Diario di una schiappa 9. Portatemi a casa!','Commedia',100,'Romanzo',NULL,'Greg è felice: sono cominciate le vacanze! Niente scuola, niente compiti. Tutto sembra filare liscio fino a quando alla mamma viene una delle sue idee. Perché non fare un bel viaggio tutti insieme? È così che Greg, Manny, Rodrick, mamma e papà partono all'' avventura. Ma gli imprevisti sono in agguato: alberghi improbabili, guasti improvvisi, gabbiani prepotenti e maiali in fuga... Tutto può succedere con gli Heffley in viaggio! Torneranno a casa sani e salvi?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2015-11-10','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869661396','Diario di una schiappa 10. Non ce la posso fare! ','Commedia',218,'Romanzo',NULL,'Povero Greg, i grandi vogliono riportarlo all''età della pietra! Non fanno che ripetere che ai vecchi tempi si stava meglio, e per dimostrarlo, lo coinvolgono in un disastro dopo l''altro: un intero fine settimana senza cellulare, computer e videogiochi, e perfino un campeggio all''antica, con i rifugi da costruire, il fuoco da accendere e strane creature che si aggirano nel bosco. Aiuto! Riuscirà Greg a sopravvivere? ','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2016-11-09','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869662584','Diario di una schiappa 11. Avanti tutta! ', 'Commedia' ,217, 'Romanzo', NULL,'Le cose si mettono di male in peggio per Greg. Papà vuole che si impegni nella banda musicale della scuola, mentre Mamma è disposta a tutto perché rinunci ai videogiochi ed esplori il suo lato creativo. E Greg? Lui vorrebbe solo godersi Halloween in santa pace: una bella festa, un costume divertente, una mega-scorta di caramelle gommose. Ma, si sa, Halloween è una festa da paura e gli orrori sono sempre dietro l angolo. Per esempio? Beh, se Mamma si imbucasse alla festa? E se il video che ha deciso di filmare con Rowley si rivelasse un disastro colossale? Povero Greg! Quanti sono i guai in arrivo, stavolta? ','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2017-11-06','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869663567','Diario di una schiappa 12. Una vacanza da panico', 'Commedia' ,217,'Romanzo', NULL, 'È inverno, fa freddo e le feste si avvicinano. I programmi di Greg? Starsene al calduccio in casa, ovviamente. Peccato che Mamma e Papà abbiano altri piani: una bella vacanza di famiglia su un''isola tropicale è quello che ci vuole. Spiagge, sole, delfini, gite in barca: un vero paradiso. Già, solo che stiamo parlando di Greg e della sua famiglia. Non penserete mica che fili tutto alla grande, vero? Basta finire dalla parte sbagliata dell''isola, e la vacanza da panico è pronta a cominciare!','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2018-11-05','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869665257','Diario di una schiappa 13. Giorni da brivido','Commedia',217,'Romanzo',NULL,'La scuola di Greg è chiusa: la neve ha bloccato tutto. Evviva! Sì, ma non troppo: l''intero quartiere si trasforma in un campo di battaglia invernale. Gruppi rivali si contendono il territorio, costruiscono fortini e combattono fino all''ultima palla di neve. E Greg? Ovviamente si trova nel bel mezzo del caos, insieme al suo fedele migliore amico Rowley. Ma il freddo non dura per sempre. E quando la neve si sarà sciolta, che fine avranno fatto Greg e Rowley? Avranno superato il gelido inverno da eroi? Una sola cosa è certa: avranno vissuto giorni da brivido.','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2019-11-04','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869666865','Diario di una schiappa 14. Disastro Totale', 'Commedia',224,'Romanzo',NULL,'Grazie a un''eredità inaspettata la famiglia Heffley ha l occasione per fare grandi cambiamenti a casa! Ma una volta buttati giù tutti i muri, sorgono un sacco di problemi: legno marcio, muffa tossica, creature indesiderate e qualcosa di ancora più sinistro... I lavori di ristrutturazione sono un vero disastro! C''è una sola cosa da fare: trasferirsi. Sarà la scelta giusta? E Greg sarà contento di vivere in un nuovo quartiere, lontano dalla sua scuola e da Rowley?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2020-11-02','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869668319','Diario di una schiappa 15. Colpito e Affondato!','Commedia',224,'Romanzo',NULL ,'Torna Greg, la Schiappa più amata del mondo. Quando Greg Heffley e la sua famiglia partono per un viaggio in camper attraverso il Paese, sono pronti a vivere la più grande delle avventure. Ma le cose non vanno secondo i piani e così si ritrovano bloccati in un campeggio che non è esattamente un paradiso estivo. Le cose peggiorano quando un violento nubifragio li scaraventa in un mare di guai... come faranno a salvare la loro vacanza?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2021-10-15','Italiano',NULL,2,NULL);

UPDATE Serie set completata =  'T' where nome = 'Diario di una Schiappa';




INSERT INTO SERIE(Nome, NumLibri, Completata)
VALUES ('Il trono di spade',0,'False');


/*Libri*/
INSERT INTO Libro VALUES ('9788804662136','Il trono di spade (Vol. 1)','Avventura',425,'Romanzo',NULL,' In una terra fuori dal mondo, dove le estati e gli inverni possono durare intere generazioni, sta per esplodere un immane conflitto. Sul Trono di Spade, nel Sud caldo e opulento, siede Robert Baratheon. L''ha conquistato dopo una guerra sanguinosa, togliendolo all''ultimo, folle re della dinastia Targaryen, i signori dei draghi. Ma il suo potere è ora minacciato: all''estremo Nord la Barriera – una muraglia eretta per difendere il regno da animali primordiali e, soprattutto, dagli Estranei – sembra vacillare. Si dice che gli Estranei siano scomparsi da secoli. Ma se è vero, chi sono quegli esseri con gli occhi così innaturalmente azzurri e gelidi, nascosti tra le ombre delle foreste, che rubano la vita o il sonno a chi ha la mala di incontrarli?', 'Cartaceo,Digitale,AudioLibro', 'Mondadori','George R. R. Martin', '2012-01-24','Italiana',NULL,3,2); 

INSERT INTO Libro VALUES ('9788804662137','Il trono di spade (Vol. 2)','Avventura',425,'Romanzo',NULL,'Nel cielo dei Sette Regni, travolti da una guerra devastatrice, compare una cometa dal sinistro colore di sangue. È l ennesimo segno di immani catastrofi che si stanno preparando?  estate dell abbondanza sembra ormai definitivamente passata, e ben quattro condottieri si contendono ferocemente il Trono di Spade. Intanto al di là del mare caldo l orgogliosa principessa in esilio Daenerys Targaryen è pronta a rischiare tutto per la corona che le appartiene di diritto. Solo per lei, forse, la cometa di sangue non è un presagio di tragedia ma l araldo della riscossa Mentre lo scontro continua, qualcuno tesse un inesorabile tela di morte. All estremo Nord, oltre la Barriera di ghiaccio, forze oscure vanno facendosi sempre più minacciose... In una terra di sinistra magia e violenza, ma anche di eroismo e passione, è ambientato il secondo volume della saga Le cronache del ghiaccio e del Fuoco, l''attesissimo seguito de Il Trono di Spade e Il Grande Inverno.', 'Cartaceo,Digitale,AudioLibro', 'Mondadori','George R. R. Martin', '2013-01-24','Italiana',NULL,3,NULL); 

INSERT INTO Libro VALUES ('9788804662138','Il trono di spade (Vol. 3)','Avventura',425,'Romanzo',NULL,'Dopo la morte di re Renly Baratheon gli avversari che si contendono il Trono di Spade sono ridotti a quattro. Il gioco di alleanze, inganni e tradimenti si fa sempre più spietato, sempre più labirintico, e l ambizione dei contendenti non ha limite. Sui quattro re e sui paesaggi già devastati dalla guerra incombe la più terribile delle minacce: dall''estremo nord un immane orda di barbari e giganti, mammut e metamorfi sta lentamente scendendo verso i Sette Regni. E con il popolo libero dei bruti, un pericolo ancora più spaventoso si avvicina: gli Estranei, guerrieri soprannaturali che non temono la morte. Perché alla morte già appartengono? Gli indeboliti, dilaniati guardiani della notte sanno che i loro giorni potrebbero essere contati. Spetterà a Jon Snow, il bastardo di Grande Inverno, ergersi per una disperata, eroica, ultima difesa. Forse, la guerra per il potere supremo è ancora tutta da giocare.', 'Cartaceo,Digitale,AudioLibro', 'Mondadori','George R. R. Martin', '2014-01-24','Italiana',NULL,3,NULL); 



INSERT INTO Libro VALUES ('9788804662139','Il trono di spade (Vol. 4)','Avventura',425,'Romanzo',NULL, 'In spettrali campi di battaglia e tetre fortezze in rovina, fra città tramutate in cimiteri e terre ridotte a ossari, la spaventosa guerra dei cinque re volge ormai al termine. La Casa Lannister e i suoi alleati appaiono vincitori. Eppure, nei Sette Regni, qualcosa ancora si agita. Mentre corvi in forma umana si raccolgono per un festino di ceneri, nuovi, temerari complotti vengono orditi e nuove, pericolose alleanze prendono forma. In questa apparentemente consolidata pace del re  forze inattese sono pronte a sferrare attacchi cruenti. Guidati dal famigerato re Occhio-di-corvo, gli uomini di ferro, eredi di un culto guerriero dimenticato da secoli, si sono lanciati all''invasione del sudovest del reame, costringendo la regina Cersei e il Trono di Spade ad affrontare un''inedita prova di forza. E dalle brume di una memoria lasciata troppo a lungo sepolta, un antica, sinistra profezia potrebbe minacciare la stessa regina.' , 'Cartaceo,Digitale,AudioLibro', 'Mondadori','George R. R. Martin','2015-01-24', 'Italiana',NULL,3,9); 

INSERT INTO Libro VALUES ('9788804662140','Il trono di spade (Vol. 5)','Avventura',425,'Romanzo',NULL, 'La vittoria del leone dei Lannister ha lasciato un''interminabile scia di sangue: sepolto l''infame lord Tywin, assassinato dal proprio figlio nano, finita in catene la regina Cersei, seduto il piccolo re Tommen su un trono di lame pronte a ucciderlo, il destino dell''intero continente occidentale è di nuovo in bilico. Sulla remota Barriera di ghiaccio il temerario Jon Snow è costretto a consolidare con le armi il suo rango di lord comandante dei guardiani della notte mentre, al di là del Mare Stretto, Daenerys Targaryen, l intrepida Regina dei Draghi, continua a difendere il proprio dominio contro orde di nemici antichi e nuovi. In fuga verso le città libere, il parricida Tyrion Lannister potrebbe essere la chiave di volta della restaurazione della mai realmente estinta dinastia del Drago. Tutto questo però potrebbe rivelarsi disperatamente inutile. Perché ora, veramente... l inverno sta arrivando.','Cartaceo,Digitale,AudioLibro', 'Mondadori','George R. R. Martin', '2016-01-24','Italiana',NULL,3,NULL); 





INSERT INTO Libro VALUES 
('9788891808561','Io Me e Me Stesso', 'Psicologico',141,'Didattico','Psicologia','Siete pronti a esclamare babbabia leggendo le pagine di questo libro? Qui ho raccolto i miei pensieri, i miei segreti e i consigli da condividere con voi che mi supportate ogni giorno. Buona lettura e un abbraccio dal vostro CiccioGamer89.','Cartaceo','Mondadori','Mirko Alessandrini','2017-01-31','Italiana',NULL,NULL,1); 

INSERT INTO Libro VALUES 
('9788891808562','CiccioGamer89 Presenta Fortnite. Trucchi e Segreti', 'Psicologico',141,'Didattico','Psicologia', 'Vuoi iniziare a collezionare Vittorie Reali in Battaglia Reale di Fortnite? Ti svegli nel cuore della notte con l''incubo di una missione di Salvare il Mondo? Paracadute sulle spalle e Let''s Go! È il momento di aprire la guida firmata da CiccioGamer89 – uno dei gamer più famosi d''Italia e uno tra i migliori giocatori di Fortnite su YouTube. 144 pagine dedicate ai segreti del fenomeno videoludico del nostro tempo, con centinaia di consigli esplosivi per potenziare le tue abilità!.','Cartaceo', 'Magazzini Salani','Mirko Alessandrini', '27-09-2018','Italiana',NULL,NULL,NULL); 

INSERT INTO Libro VALUES 
('9788891808563','In cucina con Ciccio', 'Ricettacolo',135 ,'Didattico', 'Cucina' , 'I burger più gustosi ma non solo: dai mini sandwich avocado e salmone alla pagnotta all''amatriciana, dai supplì con stracciata di bufala ai funghi ripieni, per finire con un bel maritozzo alla panna. Un viaggio tra i fornelli della mia cucina con tutte le mie ricette preferite, pronte a sorprenderci grazie allo speciale tocco alla Ciccio.
Che tu sia un principiante o un esperto, che tu voglia seguire le mie ricette o personalizzarle con un tocco di fantasia, tutto ciò che devi fare è indossare un grembiule e… iniziare a spadellare!
E che fai te ne privi?','Cartaceo', 'Magazzini Salani','Mirko Alessandrini', '15-06-2020','Italiana',NULL,NULL,8); 



INSERT INTO Acquisto (Nome, Tipo, Indirizzo)
VALUES ('Biblioteca Nazionale Centrale di Firenze', 'Libreria', 'Piazza dei Cavalleggeri, 1, 34567 Firenze');

INSERT INTO Acquisto (Nome, Tipo, Url)
VALUES ( 'Amazon', 'Sito web', 'www.amazon.com');

INSERT INTO Acquisto (Nome, Tipo, Indirizzo)
VALUES ( 'Biblioteca Nazionale Centrale di Roma', 'Libreria',  'Viale Castro Pretorio, 105, 20121 Milano');

INSERT INTO Acquisto (Nome, Tipo, Url)
VALUES ('IBS', 'Sito web', 'www.ibs.it');

INSERT INTO Acquisto (Nome, Tipo, Indirizzo)
VALUES ('Libreria Feltrinelli', 'Libreria', 'Piazza Piemonte, 6, 45678 Napoli');

INSERT INTO Acquisto (Nome, Tipo, Url)
VALUES ('eBay', 'Sito web', 'www.ebay.com');

INSERT INTO Acquisto (Nome, Tipo, Indirizzo)
VALUES ('Biblioteca Comunale', 'Libreria', 'Piazza XX Settembre, 15, 47812 Roma');

INSERT INTO Acquisto (Nome, Tipo, Url)
VALUES ('Alibris', 'Sito web', 'www.alibris.com');



call add_disponibile_l('9788804662136','1+3');
call add_disponibile_l('9788804662137','1+3');
call add_disponibile_l('9788804662138','1+3');
call add_disponibile_l('9788804662139','1+3');
call add_disponibile_l('9788804662140','1+3');




INSERT INTO Rivista VALUES('National Geographic Magazine','2022-01-20','Ciro quokka','I Procioni');

INSERT INTO Rivista VALUES('Le scienze','2009-02-24','Paola Tedesca','I quanti');

INSERT INTO Rivista VALUES('Le stelle','2020-08-3','Gianluca Negroni','L''aereodinamica');

INSERT INTO Rivista VALUES('Mente e cervello','2014-08-14','Pietro Prestigiacomo','Caravaggio and L''esempio davanti del naturale');

INSERT INTO Rivista VALUES('Tuttoscienze','2020-01-31','Fabio Caressa','The CRISPR-Cas3 enzyme acts as a programmable RNA-guided DNA endonuclease');

INSERT INTO Rivista VALUES( 'Le scienze','2022-06-10','Paola Tedesca','Observation of Quantum Entanglement in the Presence of Decoherence');




INSERT INTO Conferenza (Nome, Struttura, Indirizzo, DataI, DataF, Responsabile)
VALUES ('Italian Technology Conference', 'Palazzo dei Congressi', 'Via Raffaello Sanzio, 5, 00197 Roma', '2022-10-01', '2022-10-03', 'Mario Rossi');

INSERT INTO Conferenza (Nome, Struttura, Indirizzo, DataI, DataF, Responsabile)
VALUES ( 'AI and Machine Learning Summit', 'Fiera Milano', 'Strada Statale del Sempione, 28, 20017 Milano', '2022-12-01', '2022-12-03', 'Stefano Bianchi');

INSERT INTO Conferenza (Nome, Struttura, Indirizzo, DataI, DataF, Responsabile)
VALUES ( 'Blockchain Forum', 'Palazzo Mezzanotte', 'Piazza Affari, 6, 20121 Milano', '2022-11-01', '2022-11-03', 'Paolo Neri');

INSERT INTO Conferenza (Nome, Struttura, Indirizzo, DataI, DataF, Responsabile)
VALUES ('International Symposium on Green Energy', 'Palazzo delle Nazioni', 'Via della Conciliazione, 34, 00193 Roma', '2022-09-01', '2022-09-03', 'Luigi Verde');

INSERT INTO Conferenza (Nome, Struttura, Indirizzo, DataI, DataF, Responsabile)
VALUES ('Smart Cities Conference', 'Palazzo della Borsa', 'Piazza Affari, 2, 20121 Milano', '2022-10-15', '2022-10-17', 'Roberta Neri');




INSERT INTO Articolo_Scientifico VALUES('as1','I quanti','Fisica',150, '2009-02-24','Parliamo dei quanti','Cartaceo', 'Verdi Editor' ,'Fabrizio Quaranta','Italiano',NULL,'National Geographic Magazine','2022-01-20');


INSERT INTO Articolo_Scientifico VALUES('as2','La Radioattività nucleare','Nucleare',200, '2017-05-3','Quanto può essere velenoso la radioattività nucleare','Cartaceo','Verdi Editor','Fabrizio Quaranta','Italiano',NULL,NULL,NULL);


INSERT INTO Articolo_Scientifico VALUES ('as3','L aereodinamica','Aereo Dinamica',200, '2020-08-3',' Quanto puo volare un aquila? ma sopratutto a cosa serve le alette dietro agli aeri, scopriamola in questa ricerca','Cartaceo','Mondadori','Fabrizio Quaranta','Italiano',NULL,'Le scienze','2009-02-24');


INSERT INTO Articolo_Scientifico VALUES('as4','I procioni','Animalistico',200, '20-01-2022',' I procioni conquisteranno la terra?','Cartaceo','Mondadori','Fabrizio Quaranta','Italiano',NULL,'Le stelle','2020-08-3');


INSERT INTO Articolo_Scientifico VALUES('as5','Caravaggio and L esempio davanti del naturale' ,'Artistico',445, '2014-08-14','Caravaggio''s use of models is attested by all of his early biographers, and it constitutes one of the fundamental novelties of his work. There is abundant technical evidence to suggest that some of the pictures were, quite literally, staged, with results dramatically different from the artistic norms then in fashion. This article examines the technical evidence — above all Caravaggio s well-known use of incisions — and suggests that some of the most problematic aspects of the artist''s work, including its expressive content and radical shifts in style, are at least in part the outgrowth of a revolutionary pictorial method.','Cartaceo, Online','Department of European Paintings','Keith Christiansen','Inglese',NULL,'Mente e cervello','2014-08-14');

INSERT INTO Articolo_Scientifico VALUES
('10.1038-s41586-020-2286-9','The CRISPR-Cas3 enzyme acts as a programmable RNA-guided DNA endonuclease', 'Ricerca', 12,  '2020-01-31', 'Descrizione del lavoro di ricerca sull''enzima CRISPR-Cas3', 'Cartaceo', 'Nature Publishing Group', 'Author Name', 'Inglese',NULL,NULL, NULL);

INSERT INTO Articolo_Scientifico VALUES 
('10.1103-PhysRevLett.123.240501','Observation of Quantum Entanglement in the Presence of Decoherence', 'Fisica',12,  '2022-07-01', 'A study on the observation of quantum entanglement in the presence of decoherence', 'Digitale', 'American Physical Society', 'John Doe and Jane Smith', 'Inglese',NULL, 'Le scienze','2022-06-10');




INSERT INTO Utente VALUES
('fabrizio@quaranta.it','pippo','2023-01-25');

INSERT INTO Utente VALUES
('paolo@tedesco.it','topolino','2002-10-05');

INSERT INTO Utente VALUES
('giulio@ruopolo.it','salernitana-napoli:0-2','2002-10-05');



INSERT INTO Preferiti VALUES
('giulio@ruopolo.it',1);


INSERT INTO Preferiti VALUES
('giulio@ruopolo.it',2);


INSERT INTO Preferiti VALUES
('paolo@tedesco.it',3);

