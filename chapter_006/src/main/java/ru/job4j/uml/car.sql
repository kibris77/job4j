CREATE TABLE car_body (
  	id serial primary key,
  	body_type varchar(256)
 );
 
 CREATE TABLE engine (
  	id serial primary key,
  	engine_type varchar(256)
 );
 
 CREATE TABLE transmission (
  	id serial primary key,
  	transmission_type varchar(256)
 );
 
 CREATE TABLE cars (
  	id serial primary key,
  	model varchar (256),
  	body_id integer references car_body(id),
  	engine_id integer references engine(id),
    transmission_id integer references transmission(id)
 );
 
 insert into car_body(body_type) values ('Sedan');
 
 insert into car_body(body_type) values ('Pickup');
 
 insert into car_body(body_type) values ('Hatchback');
 
 
 insert into engine(engine_type) values ('Disel');
 
 insert into engine(engine_type) values ('Benzine');
 
 insert into engine(engine_type) values ('Turbo');
 
 
 insert into transmission(transmission_type) values ('Manual');
 
 insert into transmission(transmission_type) values ('Auto');
 
 insert into transmission(transmission_type) values ('DSG');
 
 
 insert into cars(model, body_id, engine_id, transmission_id) values ('BMW', 1, 1, 2);
 
 insert into cars(model, body_id, engine_id, transmission_id) values ('AUDI', 3, 2, 3);
 
 
 SELECT c.model, cb.body_type, e.engine_type, t.transmission_type 
 FROM cars as c 
 INNER JOIN car_body as cb ON c.body_id = cb.id 
 INNER JOIN transmission as t ON c.transmission_id = t.id 
 INNER JOIN engine as e ON c.engine_id = e.id;
 
SELECT cb.body_type
FROM car_body as cb 
LEFT JOIN cars as c ON c.body_id = cb.id 
WHERE c.model IS NULL;

SELECT t.transmission_type
FROM transmission as t 
LEFT JOIN cars as c ON c.transmission_id = t.id 
WHERE c.model IS NULL;

SELECT t.transmission_type
FROM transmission as t 
LEFT JOIN cars as c ON c.transmission_id = t.id 
WHERE c.model IS NULL;
 
 