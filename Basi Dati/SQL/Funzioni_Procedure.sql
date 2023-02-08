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