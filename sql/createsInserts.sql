SET SCHEMA 'public';

DROP TABLE IF EXISTS package CASCADE;
DROP TABLE IF EXISTS accommodation CASCADE;
DROP TABLE IF EXISTS ticket CASCADE;


CREATE TABLE ticket (
    id serial NOT NULL,
    tickettype character varying(255) NOT NULL,
    origin character varying(255) NOT NULL,
    destination character varying(255) NOT NULL,
    departuredate date NOT NULL,
    returndate date,
    numpeople integer NOT NULL,
    price numeric(10,2) NOT NULL,
	PRIMARY KEY(id)
);


CREATE TABLE accommodation (
    id serial NOT NULL,
    location character varying(255) NOT NULL,
    hotel character varying(255) NOT NULL,
    entrancedate date NOT NULL,
    exitdate date NOT NULL,
    numrooms integer NOT NULL,
    numpeople integer NOT NULL,
    price numeric(10,2) NOT NULL,
	PRIMARY KEY(id)
);


CREATE TABLE package (
    id serial NOT NULL,
    ticket_id integer,
    accommodation_id integer,
    price numeric(10,2) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(ticket_id) REFERENCES ticket(id),
	FOREIGN KEY(accommodation_id) REFERENCES accommodation(id)
);


INSERT INTO ticket VALUES(1,	'ida e volta',	'Curitiba',	'California',	'2018-01-01',	'2018-02-02',	1,	500.00);
INSERT INTO ticket VALUES(2,	'ida e volta',	'Curitiba',	'Los Angeles',	'2018-05-05',	'2018-06-06',	4,	1500.00);
INSERT INTO ticket VALUES(3,	'ida e volta',	'Sao paulo',	'Manaus',	'2018-10-10',	'2018-10-15',	2,	400.00);


INSERT INTO accommodation VALUES(1,	'California',	'LEGOLAND',	'2018-01-01',	'2018-02-02',	1,	1,	300.00);
INSERT INTO accommodation VALUES(2,	'Los Angeles',	'Freehand',	'2018-05-05',	'2018-06-06',	3,	4,	750.00);
INSERT INTO accommodation VALUES(3,	'Manaus',	'Intercity',	'2018-05-05',	'2018-06-06',	2,	4,	400.00);


INSERT INTO package VALUES(1,	1,	1,	640.00);
INSERT INTO package VALUES(2,	2,	2,	1800.00);
INSERT INTO package VALUES(3,	3,	3,	640.00);



