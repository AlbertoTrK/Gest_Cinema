insert into utente(nome, cognome, username, password, email, ruolo, deleted) values ('Paolo', 'Pacello', 'Dr_pacello97', 'PaoloPac97_', 'pPacello@gmail.com', 0, false);
insert into utente(nome, cognome, username, password, email, ruolo, deleted) values ('Luca', 'Esposito', 'LuEspos95_', 'staffEspoCin95_', 'lEspoStaff@gmail.com', 1, false);
insert into utente(nome, cognome, username, password, email, ruolo, deleted) values ('Alberto', 'Mastrangelo', 'alMastr90!ADMIN', 'adminCinemaAlMast90_', 'cinemaAdmin@gmail.com', 2, false);
insert into utente(nome, cognome, username, password, email, ruolo, deleted) values ('Valerio', 'Iosca', 'Mr_Iosca91', 'Bongo91_', 'ioscotto@gmail.com', 0, 0);
insert into utente(nome, cognome, username, password, email, ruolo, deleted) values ('Mattia', 'Oliva', 'olivaM_90', 'MatOli90!', 'mOliva@gmail.com', 0, 0);

insert into genere(name, deleted) values('Horror', false);
insert into genere(name, deleted) values('Fantasy', false);
insert into genere(name, deleted) values('Thriller', false);
insert into genere(name, deleted) values('Drammatico', false);
insert into genere(name, deleted) values('Commedia', false);

insert into attore(name, surname, deleted) values ('Brad', 'Pitt', false);
insert into attore(name, surname, deleted) values ('Leonardo', 'DiCaprio', false);
insert into attore(name, surname, deleted) values ('Cillian', 'Murphy', false);
insert into attore(name, surname, deleted) values ('Zoe', 'Saldana', false);
insert into attore(name, surname, deleted) values ('Margot', 'Robbie', false);
insert into attore(name, surname, deleted) values ('Robert', 'DeNiro', false);
insert into attore(name, surname, deleted) values ('Ryan', 'Gosling', false);

insert into film(titolo, durata, descrizione, genere_id, deleted) values ('Barbie', 90, 'Film basato sul franchise Barbie', 5, false);
insert into film(titolo, durata, descrizione, genere_id, deleted) values ('Once Upon A Time In Hollywood', 150, 'Film ambientato negli anni 60 di Quentin Tarantino', 3, false);
insert into film(titolo, durata, descrizione, genere_id, deleted) values ('Oppenheimer', 90, 'Film ispirato all ideatore della bomba atomica', 4, false);
insert into film(titolo, durata, descrizione, genere_id, deleted) values ('Sleepers', 90, '', 4, false);

insert into film_attori(id_film, id_attore) values (1, 5);
insert into film_attori(id_film, id_attore) values (1, 7);
insert into film_attori(id_film, id_attore) values (2, 1);
insert into film_attori(id_film, id_attore) values (2, 2);
insert into film_attori(id_film, id_attore) values (2, 5);
insert into film_attori(id_film, id_attore) values (3, 3);
insert into film_attori(id_film, id_attore) values (4, 1);
insert into film_attori(id_film, id_attore) values (4, 6);

insert into sala(nome_sala, capienza, deleted) values ('Sala imax', 200, false);
insert into sala(nome_sala, capienza, deleted) values ('Sala 3d', 80, false);
insert into sala(nome_sala, capienza, deleted) values ('Sala standard 1', 150, false);
insert into sala(nome_sala, capienza, deleted) values ('Sala standard 2', 200, false);
insert into sala(nome_sala, capienza, deleted) values ('Sala imax 2', 100, false);

insert into spettacolo(film_id, sala_id, data, deleted, prezzo) values (1, 3, '2023-09-15 23:00:00', false, 8.50);
insert into spettacolo(film_id, sala_id, data, deleted, prezzo) values (2, 1, '2023-09-15 22:00:00', false, 9.50);
insert into spettacolo(film_id, sala_id, data, deleted, prezzo) values (3, 4, '2023-09-15 22:00:00', false, 10.50);
insert into spettacolo(film_id, sala_id, data, deleted, prezzo) values (4, 2, '2023-09-09 21:00:00', false, 11.00);

insert into biglietto(deleted, prezzo, spettacolo_id, utente_id) values (false, 12.90, 1, 1);