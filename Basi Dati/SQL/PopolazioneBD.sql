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
('9788831004169','Harry Potter e la pietra filosofale','Fantasy',368,'Romanzo',NULL,'Il primo libro della saga di Harry Potter, riccamente illustrato a colori e con otto inediti elementi interattivi, tra cui la vera lettera di Hogwarts da aprire! Questa edizione vi far?? vivere la storia di Harry Potter e la Pietra Filosofale come mai 
prima d ora: la versione integrale del romanzo di J.K. Rowling ?? accompagnata in ogni pagina da splendide illustrazioni a colori e da otto elementi interattivi. Il lettore potr?? aprire la lettera di Hogwarts come se fosse Harry, scoprire cosa si cela oltre il magico ingresso di Diagon Alley, far comparire un sontuoso banchetto nella Sala Grande e tanto altro.','Cartaceo,Digitale,AudioLibro','Salani','J.K. Rowling','2020-11-26','Italiano',NULL,1,3);

INSERT INTO Libro VALUES
('9788831003391','Harry Potter e la Camera dei Segreti','Fantasy',224,'Romanzo',NULL,'??C ?? un complotto, Harry Potter. Un complotto per far succedere le cose pi?? terribili, quest anno, alla scuola di magia e stregoneria di Hogwarts??. A Hogwarts il nuovo anno scolastico s inaugura all insegna degli enigmi: strane voci riecheggiano nei corridoi, Ginny, la sorella di Ron, sparisce nel nulla e un incantesimo colpisce i compagni di Harry, uno dopo l altro, e sembra legato a un antico mistero racchiuso nella tenebrosa camera dei segreti... Il secondo capitolo di uno dei pi?? grandi fenomeni letterari internazionali. Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosit?? sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro','Salani ','J.K. Rowling ','2020-12-23','Italiano',NULL,1,NULL);
INSERT INTO Libro VALUES
('9788831003407','Harry Potter e il prigioniero di Azkaban',' Fantasy',416,'Romanzo',NULL,'??Benvenuti sul Nottetempo, mezzo di trasporto di emergenza per maghi e streghe in difficolt??. Allungate la bacchetta, salite a bordo e vi portiamo dove volete??. Una terribile minaccia incombe sulla scuola di magia e stregoneria di Hogwarts. Sirius Black, il famigerato assassino, ?? evaso dalla prigione di Azkaban. ?? a caccia e la sua preda ?? proprio a Hogwarts, dove Harry e i suoi amici stanno per cominciare il loro terzo anno. Nonostante la sorveglianza dei Dissennatori la scuola non ?? pi?? un luogo sicuro, perch?? al suo interno si nasconde un traditore... Il terzo capitolo di uno dei pi?? grandi fenomeni letterari internazionali. Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosit?? sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro','Salani','J.K. Rowling','2020-12-29','Italiano',NULL,1,7);

INSERT INTO Libro VALUES
('9788831003414','Harry Potter e il calice di fuoco','Fantasy',672,'Romanzo',NULL,'??Le sfide saranno tre, distribuite nell arco dell anno scolastico, e metteranno alla prova i campioni in molti modi diversi ... La loro perizia magica, la loro audacia, i loro poteri deduttivi e, naturalmente, la loro capacit?? di affrontare il pericolo??. Questo che avete tra le mani ?? il volume centrale delle avventure di Harry Potter. Ormai Harry ?? un mago adolescente, vuole andarsene dalla casa dei Dursley, vuole sognare la Cercatrice di Corvonero per cui ha una cotta tremenda... E poi vuole scoprire di pi?? sulla grande competizione che si terr?? a Hogwarts e non si svolge da cento anni. Harry vuole davvero essere un normale mago di quattordici anni. Ma sfortunatamente non ?? normale, nemmeno come mago. E stavolta la differenza pu?? essere fatale. Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosit?? sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro','Salani','J.K. Rowling','2021-01-13','Italiano',NULL,1,NULL);
INSERT INTO Libro VALUES
('9788831003421','Harry Potter e l''ordine della Fenice','Fantasy',880,'Romanzo',NULL,'??Tu condividi i suoi pensieri e le sue emozioni. Il preside ritiene che questo non debba continuare. Desidera che io ti insegni a chiudere la mente al Signore Oscuro??. Il quinto anno a Hogwarts si annuncia carico di sfide difficili per Harry Potter: Lord Voldemort ?? tornato. Che cosa succeder?? ora che il Signore Oscuro ?? di nuovo in pieno possesso dei suoi terrificanti poteri? Il Ministro della Magia sembra non prendere sul serio questa spaventosa minaccia. Toccher?? a Harry organizzare la resistenza, con l aiuto degli amici di sempre e il tumultuoso coraggio dell adolescenza. Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosit?? sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro','Salani','J.K. Rowling','2021-01-18','Italiano',NULL,1,NULL);

INSERT INTO Libro VALUES
('9788831003438','Harry Potter e il principe mezzosangue','Fantasy',592,'Romanzo',NULL,'??Sospeso nel buio sopra la scuola c era il vivido teschio verde con la lingua di serpe, il marchio lasciato dai Mangiamorte tutte le volte che entravano in un edificio... Tutte le volte che uccidevano...??. ?? il sesto anno a Hogwarts e per Harry niente ?? pi?? come prima. L ultimo legame con la sua famiglia ?? troncato, perfino la scuola non ?? la dimora accogliente di un tempo. Voldemort ha radunato le sue forze e nessuno pu?? pi?? negare il suo ritorno. Harry capisce che ?? arrivato il momento di affrontare il suo destino. L ultimo atto si avvicina, sar?? all altezza di questa sfida fatale? Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosit?? sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro','Salani','J.K. Rowling','2021-01-25','Italiano',NULL,1,NULL);
INSERT INTO Libro VALUES
('9788831003445','Harry Potter e i Doni della Morte','Fantasy',688,'Romanzo',NULL,'??Consegnatemi Harry Potter?? prosegu?? la voce di Voldemort, ??e a nessuno verr?? fatto del male. Consegnatemi Harry Potter e lascer?? la scuola intatta. Consegnatemi Harry Potter e verrete ricompensati??. Il confronto finale con Voldemort ?? imminente, una grande battaglia ?? alle porte e Harry, con coraggio, compir?? ci?? che dev essere fatto. Mai i perch?? sono stati cos?? tanti e mai come in questo libro si ha la soddisfazione delle risposte. Giunti all ultima pagina si vorr?? rileggere tutto daccapo, per chiudere il cerchio, per riscoprire tutti i segreti e i significati profondi, ma soprattutto per ritardare il pi?? possibile il distacco dai meravigliosi personaggi che ci hanno accompagnato per cos?? tanto tempo e che non hanno ancora smesso di incantarci. Edizione speciale con contenuti inediti: la mappa di Hogwarts, il glossario, curiosit?? sui fondatori di Hogwarts.','Cartaceo,Digitale,AudioLibro','Salani','J.K. Rowling','2021-02-03','Italiano',NULL,1,NULL);
INSERT INTO Libro VALUES
('9788893818407','Harry Potter e la maledizione dell erede','Fantasy',384,'Romanzo',NULL,'L ottava storia. Diciannove anni dopo. ?? sempre stato difficile essere Harry Potter e non ?? molto pi?? facile ora, da impiegato al Ministero della Magia, oberato di lavoro, marito e padre di tre figli in et?? scolare. Mentre Harry Potter fa i conti con un passato che si rifiuta di rimanere tale, Albus, suo secondogenito, deve lottare con il peso di un eredit?? familiare che non ha mai voluto. Quando passato e presente si fondono in un oscura minaccia, padre e figlio apprendono una scomoda verit??: il pericolo proviene a volte da luoghi inaspettati. Basato su una storia originale di J.K. Rowling, John Tiffany e Jack Thorne, lo script di Harry Potter e la Maledizione dell Erede ?? stato pubblicato come edizione speciale in occasione della premi??re che si ?? tenuta nel West End di Londra nell estate del 2016. Lo spettacolo ha ricevuto un accoglienza entusiastica da pubblico e critica e il libro ?? immediatamente diventato un bestseller internazionale. ','Cartaceo,Digitale,AudioLibro','Salani','J.K. Rowling','2021-02-22','Italiano',NULL,1,NULL);

UPDATE Serie set completata = TRUE  where nome = 'Harry Potter';


INSERT INTO SERIE(Nome, NumLibri, Completata)
VALUES ('Diario di una Schiappa',0,'False');

INSERT INTO Libro VALUES
('9788880334392','Diario di una schiappa 1','Commedia',217,'Romanzo',NULL,'Essere un ragazzo ?? un mestiere complicato. Nessuno lo sa meglio di Greg, che ha iniziato la scuola media e si ritrova in mezzo a compagni ben pi?? alti di lui, ragazze improvvisamente grandi, e amici con cui ?? cos?? difficile andare d''accordo. Diario di una schiappa ?? la cronaca delle avventure quotidiane di un imprevedibile e simpaticissimo antieroe','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2008-02-05','Italiano',NULL,'2',4);

INSERT INTO Libro VALUES
('9788803348422','Diario di una schiappa 2. La legge dei pi?? grandi','Commedia',216,'Romanzo',NULL,'Non chiedete a Greg come sono andate le vacanze. Decisamente preferisce non parlarne e poi c''?? stato un episodio davvero imbarazzante che nessuno dovrebbe scoprire. Peccato che suo fratello Rodrick conosca tutti i dettagli e glielo ricordi in continuazione. Tra vecchi e nuovi amici, scherzi tremendi a scuola e segreti che vengono scoperti, essere un ragazzo ?? un mestiere sempre pi?? complicato.','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2009-02-03','Italiano',NULL,2,5);

INSERT INTO Libro VALUES
('9788880335214','Diario di una schiappa 3. Ora basta!','Commedia',217,'Romanzo',NULL,'Non c ?? niente da fare: Greg rimarr?? sempre una schiappa e a lui tutto sommato sta bene. Qualcuno per?? dovrebbe spiegarlo a suo padre Frank, che vuole iscriverlo a sport di squadra e ad altre attivit?? che ne facciano un vero uomo. Ora pensa addirittura all Accademia Militare! Come far?? Greg a schivare i deliranti propositi di suo padre, a conquistare la ragazza che gli piace e ad arrivare sano e salvo alle vacanze estive? Greg capisce che questa volta ?? alla resa dei conti, fa di tutto per salvarsi, e noi come sempre facciamo il tifo per lui. Ormai Diario di una schiappa ?? un must per i ragazzi, che racconta la loro vita quotidiana con humour, intelligenza e leggerezza. ','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2009-02-03','Italiano',NULL,2,6);

INSERT INTO Libro VALUES
('9788880335719','Diario di una schiappa 4. Vita da cani','Commedia',218,'Romanzo',NULL,'Sono arrivate le vacanze estive, il tempo ?? fantastico e tutti i ragazzi vivono all aria aperta. Ma cosa fa Greg? Chiuso in casa, con i videogiochi e le tende tirate, sta realizzando il suo sogno: un periodo senza obblighi e responsabilit??. Sua madre per?? ha un idea tutta diversa dell''estate perfetta... un idea fatta di attivit?? all''aperto e gite con la famiglia. Chi avr?? la meglio?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2011-02-01','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788880336181','Diario di una schiappa 5. La dura verit??' ,'Commedia',218,'Romanzo',NULL,'Greg ha sempre avuto una gran fretta di crescere. Ma diventare grandi ?? davvero uno spasso come ci si aspetta? Fra riunioni di famiglia, lezioni sui fatti della vita, una piccola guerra con la nuova colf e un apocalittica notte trascorsa a scuola, Greg dovr?? affrontare nuove ed esilaranti avventure senza il fedele amico Rowley. Ce la far?? da solo?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2012-02-01','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788880336655','Diario di una schiappa 6. Si salvi chi pu??','Commedia',218,'Romanzo',NULL,'Greg Heffley ?? nei guai. Qualcuno ha sporcato i muri della scuola e lui ?? il primo sospettato. Ma Greg ?? sicuro di essere innocente! O almeno, quasi innocente... Quando la verit?? sta per essere scoperta, una tempesta di neve si abbatte sulla citt?? e la famiglia Heffley rimane bloccata in casa. Greg sa che allo sciogliersi della neve dovr?? affrontare le autorit??, ma nel frattempo... c''?? una punizione peggiore del passare le vacanze in trappola con Manny, Rodrick e Mamma?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2013-01-03','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788880337775','Diario di una schiappa 7. Guai in arrivo!','Commedia',218,'Romanzo',NULL,'Povero Greg, anche questa volta sono guai, ma guai seri! La scuola ha organizzato una grande festa, e se non vuole essere tagliato fuori, deve affrontare un''impresa quasi impossibile. Imparare a ballare? S??, ma non solo: Greg deve procurarsi una ragazza. Fra i consigli dello zio Gary, un radicale cambio di look e una serie di fiaschi colossali, le cose sembrano mettersi male. Per fortuna che c''?? Rowley! Con il suo aiuto, Greg trova il modo di svoltare la serata, ma... siamo sicuri che non ci siano altri guai in arrivo?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2014-01-03','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788880338949','Diario di una schiappa 8. Sfortuna nera.','Commedia',217,'Romanzo',NULL,'Per Greg ?? un momento difficile. Il suo migliore amico Rowley passa le giornate con la fidanzata e non ha pi?? tempo per lui. E trovarsi dei nuovi amici alla scuola media ?? una vera impresa. Per migliorare la sua sorte, Greg decide di affidarsi alla Palla magica. Basta fare una domanda e la palla fornisce tutte le risposte. Evviva! All inizio sembra funzionare a meraviglia, ma la Palla magica riuscir?? a cambiare davvero le cose?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2014-11-13','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869660337','Diario di una schiappa 9. Portatemi a casa!','Commedia',100,'Romanzo',NULL,'Greg ?? felice: sono cominciate le vacanze! Niente scuola, niente compiti. Tutto sembra filare liscio fino a quando alla mamma viene una delle sue idee. Perch?? non fare un bel viaggio tutti insieme? ?? cos?? che Greg, Manny, Rodrick, mamma e pap?? partono all'' avventura. Ma gli imprevisti sono in agguato: alberghi improbabili, guasti improvvisi, gabbiani prepotenti e maiali in fuga... Tutto pu?? succedere con gli Heffley in viaggio! Torneranno a casa sani e salvi?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2015-11-10','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869661396','Diario di una schiappa 10. Non ce la posso fare! ','Commedia',218,'Romanzo',NULL,'Povero Greg, i grandi vogliono riportarlo all''et?? della pietra! Non fanno che ripetere che ai vecchi tempi si stava meglio, e per dimostrarlo, lo coinvolgono in un disastro dopo l''altro: un intero fine settimana senza cellulare, computer e videogiochi, e perfino un campeggio all''antica, con i rifugi da costruire, il fuoco da accendere e strane creature che si aggirano nel bosco. Aiuto! Riuscir?? Greg a sopravvivere? ','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2016-11-09','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869662584','Diario di una schiappa 11. Avanti tutta! ', 'Commedia' ,217, 'Romanzo', NULL,'Le cose si mettono di male in peggio per Greg. Pap?? vuole che si impegni nella banda musicale della scuola, mentre Mamma ?? disposta a tutto perch?? rinunci ai videogiochi ed esplori il suo lato creativo. E Greg? Lui vorrebbe solo godersi Halloween in santa pace: una bella festa, un costume divertente, una mega-scorta di caramelle gommose. Ma, si sa, Halloween ?? una festa da paura e gli orrori sono sempre dietro l angolo. Per esempio? Beh, se Mamma si imbucasse alla festa? E se il video che ha deciso di filmare con Rowley si rivelasse un disastro colossale? Povero Greg! Quanti sono i guai in arrivo, stavolta? ','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2017-11-06','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869663567','Diario di una schiappa 12. Una vacanza da panico', 'Commedia' ,217,'Romanzo', NULL, '?? inverno, fa freddo e le feste si avvicinano. I programmi di Greg? Starsene al calduccio in casa, ovviamente. Peccato che Mamma e Pap?? abbiano altri piani: una bella vacanza di famiglia su un''isola tropicale ?? quello che ci vuole. Spiagge, sole, delfini, gite in barca: un vero paradiso. Gi??, solo che stiamo parlando di Greg e della sua famiglia. Non penserete mica che fili tutto alla grande, vero? Basta finire dalla parte sbagliata dell''isola, e la vacanza da panico ?? pronta a cominciare!','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2018-11-05','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869665257','Diario di una schiappa 13. Giorni da brivido','Commedia',217,'Romanzo',NULL,'La scuola di Greg ?? chiusa: la neve ha bloccato tutto. Evviva! S??, ma non troppo: l''intero quartiere si trasforma in un campo di battaglia invernale. Gruppi rivali si contendono il territorio, costruiscono fortini e combattono fino all''ultima palla di neve. E Greg? Ovviamente si trova nel bel mezzo del caos, insieme al suo fedele migliore amico Rowley. Ma il freddo non dura per sempre. E quando la neve si sar?? sciolta, che fine avranno fatto Greg e Rowley? Avranno superato il gelido inverno da eroi? Una sola cosa ?? certa: avranno vissuto giorni da brivido.','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2019-11-04','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869666865','Diario di una schiappa 14. Disastro Totale', 'Commedia',224,'Romanzo',NULL,'Grazie a un''eredit?? inaspettata la famiglia Heffley ha l occasione per fare grandi cambiamenti a casa! Ma una volta buttati gi?? tutti i muri, sorgono un sacco di problemi: legno marcio, muffa tossica, creature indesiderate e qualcosa di ancora pi?? sinistro... I lavori di ristrutturazione sono un vero disastro! C''?? una sola cosa da fare: trasferirsi. Sar?? la scelta giusta? E Greg sar?? contento di vivere in un nuovo quartiere, lontano dalla sua scuola e da Rowley?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2020-11-02','Italiano',NULL,2,NULL);

INSERT INTO Libro VALUES
('9788869668319','Diario di una schiappa 15. Colpito e Affondato!','Commedia',224,'Romanzo',NULL ,'Torna Greg, la Schiappa pi?? amata del mondo. Quando Greg Heffley e la sua famiglia partono per un viaggio in camper attraverso il Paese, sono pronti a vivere la pi?? grande delle avventure. Ma le cose non vanno secondo i piani e cos?? si ritrovano bloccati in un campeggio che non ?? esattamente un paradiso estivo. Le cose peggiorano quando un violento nubifragio li scaraventa in un mare di guai... come faranno a salvare la loro vacanza?','Cartaceo,Digitale,AudioLibro','Il Castoro','Jeff Kinney','2021-10-15','Italiano',NULL,2,NULL);

UPDATE Serie set completata =  'T' where nome = 'Diario di una Schiappa';




INSERT INTO SERIE(Nome, NumLibri, Completata)
VALUES ('Il trono di spade',0,'False');


/*Libri*/
INSERT INTO Libro VALUES ('9788804662136','Il trono di spade (Vol. 1)','Avventura',425,'Romanzo',NULL,' In una terra fuori dal mondo, dove le estati e gli inverni possono durare intere generazioni, sta per esplodere un immane conflitto. Sul Trono di Spade, nel Sud caldo e opulento, siede Robert Baratheon. L''ha conquistato dopo una guerra sanguinosa, togliendolo all''ultimo, folle re della dinastia Targaryen, i signori dei draghi. Ma il suo potere ?? ora minacciato: all''estremo Nord la Barriera ??? una muraglia eretta per difendere il regno da animali primordiali e, soprattutto, dagli Estranei ??? sembra vacillare. Si dice che gli Estranei siano scomparsi da secoli. Ma se ?? vero, chi sono quegli esseri con gli occhi cos?? innaturalmente azzurri e gelidi, nascosti tra le ombre delle foreste, che rubano la vita o il sonno a chi ha la mala di incontrarli?', 'Cartaceo,Digitale,AudioLibro', 'Mondadori','George R. R. Martin', '2012-01-24','Italiano',NULL,3,2); 

INSERT INTO Libro VALUES ('9788804662137','Il trono di spade (Vol. 2)','Avventura',425,'Romanzo',NULL,'Nel cielo dei Sette Regni, travolti da una guerra devastatrice, compare una cometa dal sinistro colore di sangue. ?? l ennesimo segno di immani catastrofi che si stanno preparando?  estate dell abbondanza sembra ormai definitivamente passata, e ben quattro condottieri si contendono ferocemente il Trono di Spade. Intanto al di l?? del mare caldo l orgogliosa principessa in esilio Daenerys Targaryen ?? pronta a rischiare tutto per la corona che le appartiene di diritto. Solo per lei, forse, la cometa di sangue non ?? un presagio di tragedia ma l araldo della riscossa Mentre lo scontro continua, qualcuno tesse un inesorabile tela di morte. All estremo Nord, oltre la Barriera di ghiaccio, forze oscure vanno facendosi sempre pi?? minacciose... In una terra di sinistra magia e violenza, ma anche di eroismo e passione, ?? ambientato il secondo volume della saga Le cronache del ghiaccio e del Fuoco, l''attesissimo seguito de Il Trono di Spade e Il Grande Inverno.', 'Cartaceo,Digitale,AudioLibro', 'Mondadori','George R. R. Martin', '2013-01-24','Italiano',NULL,3,NULL); 

INSERT INTO Libro VALUES ('9788804662138','Il trono di spade (Vol. 3)','Avventura',425,'Romanzo',NULL,'Dopo la morte di re Renly Baratheon gli avversari che si contendono il Trono di Spade sono ridotti a quattro. Il gioco di alleanze, inganni e tradimenti si fa sempre pi?? spietato, sempre pi?? labirintico, e l ambizione dei contendenti non ha limite. Sui quattro re e sui paesaggi gi?? devastati dalla guerra incombe la pi?? terribile delle minacce: dall''estremo nord un immane orda di barbari e giganti, mammut e metamorfi sta lentamente scendendo verso i Sette Regni. E con il popolo libero dei bruti, un pericolo ancora pi?? spaventoso si avvicina: gli Estranei, guerrieri soprannaturali che non temono la morte. Perch?? alla morte gi?? appartengono? Gli indeboliti, dilaniati guardiani della notte sanno che i loro giorni potrebbero essere contati. Spetter?? a Jon Snow, il bastardo di Grande Inverno, ergersi per una disperata, eroica, ultima difesa. Forse, la guerra per il potere supremo ?? ancora tutta da giocare.', 'Cartaceo,Digitale,AudioLibro', 'Mondadori','George R. R. Martin', '2014-01-24','Italiano',NULL,3,NULL); 



INSERT INTO Libro VALUES ('9788804662139','Il trono di spade (Vol. 4)','Avventura',425,'Romanzo',NULL, 'In spettrali campi di battaglia e tetre fortezze in rovina, fra citt?? tramutate in cimiteri e terre ridotte a ossari, la spaventosa guerra dei cinque re volge ormai al termine. La Casa Lannister e i suoi alleati appaiono vincitori. Eppure, nei Sette Regni, qualcosa ancora si agita. Mentre corvi in forma umana si raccolgono per un festino di ceneri, nuovi, temerari complotti vengono orditi e nuove, pericolose alleanze prendono forma. In questa apparentemente consolidata pace del re  forze inattese sono pronte a sferrare attacchi cruenti. Guidati dal famigerato re Occhio-di-corvo, gli uomini di ferro, eredi di un culto guerriero dimenticato da secoli, si sono lanciati all''invasione del sudovest del reame, costringendo la regina Cersei e il Trono di Spade ad affrontare un''inedita prova di forza. E dalle brume di una memoria lasciata troppo a lungo sepolta, un antica, sinistra profezia potrebbe minacciare la stessa regina.' , 'Cartaceo,Digitale,AudioLibro', 'Mondadori','George R. R. Martin','2015-01-24', 'Italiano',NULL,3,9); 

INSERT INTO Libro VALUES ('9788804662140','Il trono di spade (Vol. 5)','Avventura',425,'Romanzo',NULL, 'La vittoria del leone dei Lannister ha lasciato un''interminabile scia di sangue: sepolto l''infame lord Tywin, assassinato dal proprio figlio nano, finita in catene la regina Cersei, seduto il piccolo re Tommen su un trono di lame pronte a ucciderlo, il destino dell''intero continente occidentale ?? di nuovo in bilico. Sulla remota Barriera di ghiaccio il temerario Jon Snow ?? costretto a consolidare con le armi il suo rango di lord comandante dei guardiani della notte mentre, al di l?? del Mare Stretto, Daenerys Targaryen, l intrepida Regina dei Draghi, continua a difendere il proprio dominio contro orde di nemici antichi e nuovi. In fuga verso le citt?? libere, il parricida Tyrion Lannister potrebbe essere la chiave di volta della restaurazione della mai realmente estinta dinastia del Drago. Tutto questo per?? potrebbe rivelarsi disperatamente inutile. Perch?? ora, veramente... l inverno sta arrivando.','Cartaceo,Digitale,AudioLibro', 'Mondadori','George R. R. Martin', '2016-01-24','Italiano',NULL,3,NULL); 





INSERT INTO Libro VALUES 
('9788891808561','Io Me e Me Stesso', 'Psicologico',141,'Didattico','Psicologia','Siete pronti a esclamare babbabia leggendo le pagine di questo libro? Qui ho raccolto i miei pensieri, i miei segreti e i consigli da condividere con voi che mi supportate ogni giorno. Buona lettura e un abbraccio dal vostro CiccioGamer89.','Cartaceo','Mondadori','Mirko Alessandrini','2017-01-31','Italiano',NULL,NULL,1); 

INSERT INTO Libro VALUES 
('9788891808562','CiccioGamer89 Presenta Fortnite. Trucchi e Segreti', 'Psicologico',141,'Didattico','Psicologia', 'Vuoi iniziare a collezionare Vittorie Reali in Battaglia Reale di Fortnite? Ti svegli nel cuore della notte con l''incubo di una missione di Salvare il Mondo? Paracadute sulle spalle e Let''s Go! ?? il momento di aprire la guida firmata da CiccioGamer89 ??? uno dei gamer pi?? famosi d''Italia e uno tra i migliori giocatori di Fortnite su YouTube. 144 pagine dedicate ai segreti del fenomeno videoludico del nostro tempo, con centinaia di consigli esplosivi per potenziare le tue abilit??!.','Cartaceo', 'Magazzini Salani','Mirko Alessandrini', '27-09-2018','Italiano',NULL,NULL,NULL); 

INSERT INTO Libro VALUES 
('9788891808563','In cucina con Ciccio', 'Ricettacolo',135 ,'Didattico', 'Cucina' , 'I burger pi?? gustosi ma non solo: dai mini sandwich avocado e salmone alla pagnotta all''amatriciana, dai suppl?? con stracciata di bufala ai funghi ripieni, per finire con un bel maritozzo alla panna. Un viaggio tra i fornelli della mia cucina con tutte le mie ricette preferite, pronte a sorprenderci grazie allo speciale tocco alla Ciccio.
Che tu sia un principiante o un esperto, che tu voglia seguire le mie ricette o personalizzarle con un tocco di fantasia, tutto ci?? che devi fare ?? indossare un grembiule e??? iniziare a spadellare!
E che fai te ne privi?','Cartaceo', 'Magazzini Salani','Mirko Alessandrini', '15-06-2020','Italiano',NULL,NULL,8); 



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

INSERT INTO Rivista VALUES('Le scienze','2022-06-10','Paola Tedesca','Observation of Quantum Entanglement in the Presence of Decoherence');




INSERT INTO Conferenza (Nome, Struttura, Indirizzo, DataI, DataF, Responsabile)
VALUES ('Italian Technology Conference', 'Palazzo dei Congressi', 'Via Raffaello Sanzio, 5, 00197 Roma', '2022-10-01', '2022-10-03', 'Mario Rossi');

INSERT INTO Conferenza (Nome, Struttura, Indirizzo, DataI, DataF, Responsabile)
VALUES ('AI and Machine Learning Summit', 'Fiera Milano', 'Strada Statale del Sempione, 28, 20017 Milano', '2022-12-01', '2022-12-03', 'Stefano Bianchi');

INSERT INTO Conferenza (Nome, Struttura, Indirizzo, DataI, DataF, Responsabile)
VALUES ('Blockchain Forum', 'Palazzo Mezzanotte', 'Piazza Affari, 6, 20121 Milano', '2022-11-01', '2022-11-03', 'Paolo Neri');

INSERT INTO Conferenza (Nome, Struttura, Indirizzo, DataI, DataF, Responsabile)
VALUES ('International Symposium on Green Energy', 'Palazzo delle Nazioni', 'Via della Conciliazione, 34, 00193 Roma', '2022-09-01', '2022-09-03', 'Luigi Verde');

INSERT INTO Conferenza (Nome, Struttura, Indirizzo, DataI, DataF, Responsabile)
VALUES ('Smart Cities Conference', 'Palazzo della Borsa', 'Piazza Affari, 2, 20121 Milano', '2022-10-15', '2022-10-17', 'Roberta Neri');




INSERT INTO Articolo_Scientifico VALUES('as1','I quanti','Fisica',150, '2009-02-24','Parliamo dei quanti','Cartaceo', 'Verdi Editor' ,'Fabrizio Quaranta','Italiano',NULL,'National Geographic Magazine','2022-01-20');


INSERT INTO Articolo_Scientifico VALUES('as2','La Radioattivit?? nucleare','Nucleare',200, '2017-05-3','Quanto pu?? essere velenoso la radioattivit?? nucleare','Cartaceo','Verdi Editor','Fabrizio Quaranta','Italiano',NULL,NULL,NULL);


INSERT INTO Articolo_Scientifico VALUES ('as3','L aereodinamica','Aereo Dinamica',200, '2020-08-3',' Quanto puo volare un aquila? ma sopratutto a cosa serve le alette dietro agli aeri, scopriamola in questa ricerca','Cartaceo','Mondadori','Fabrizio Quaranta','Italiano',NULL,'Le scienze','2009-02-24');


INSERT INTO Articolo_Scientifico VALUES('as4','I procioni','Animalistico',200, '20-01-2022',' I procioni conquisteranno la terra?','Cartaceo','Mondadori','Fabrizio Quaranta','Italiano',NULL,'Le stelle','2020-08-3');


INSERT INTO Articolo_Scientifico VALUES('as5','Caravaggio and L esempio davanti del naturale' ,'Artistico',445, '2014-08-14','Caravaggio''s use of models is attested by all of his early biographers, and it constitutes one of the fundamental novelties of his work. There is abundant technical evidence to suggest that some of the pictures were, quite literally, staged, with results dramatically different from the artistic norms then in fashion. This article examines the technical evidence ??? above all Caravaggio s well-known use of incisions ??? and suggests that some of the most problematic aspects of the artist''s work, including its expressive content and radical shifts in style, are at least in part the outgrowth of a revolutionary pictorial method.','Cartaceo, Online','Department of European Paintings','Keith Christiansen','Inglese',NULL,'Mente e cervello','2014-08-14');

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
