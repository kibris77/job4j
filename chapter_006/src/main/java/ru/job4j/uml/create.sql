CREATE DATABASE tracker;

CREATE TABLE users (
	id serial primary key,
	login varchar(256),
	password varchar(256),
	role_id integer references role(id)
);

CREATE TABLE roles (
	id serial primary key,
	role varchar(256),
);

CREATE TABLE rules (
	id serial primary key,
	rules varchar(256),
);

CREATE TABLE role_rules (
	id serial primary key,
	role_id integer references role(id),
	rule_id integer references rules(id)
);

CREATE TABLE items (
	id serial primary key,
	item text,
	date_time timestamp,
	users_id integer references users(id),
	category_id integer references categorys(id),
	status_id integer references statuses(id)
);

CREATE TABLE categorys (
	id serial primary key,
	category varchar(256)
);

CREATE TABLE statuses (
	id serial primary key,
	status varchar(256)
);

CREATE TABLE comments (
	id serial primary key,
	comments text,
	date_time timestamp,
	item_id integer references items(id)
);

CREATE TABLE attach (
	id serial primary key,
	filename varchar(256),
	item_id integer references items(id)
);

INSERT INTO roles values('administrator');
INSERT INTO roles values('moderator');
INSERT INTO roles values('guest');

INSERT INTO rules values('resd');
INSERT INTO rules values('write');
INSERT INTO rules values('edit');
INSERT INTO rules values('delete');

INSERT INTO role_rules values(1, 1);
INSERT INTO role_rules values(1, 2);
INSERT INTO role_rules values(1, 3);
INSERT INTO role_rules values(1, 4);

INSERT INTO role_rules values(2, 1);
INSERT INTO role_rules values(2, 2);
INSERT INTO role_rules values(2, 3);

INSERT INTO role_rules values(3, 1);


INSERT INTO statuses values('new');
INSERT INTO statuses values('work');
INSERT INTO statuses values('finish');

INSERT INTO categorys values('category1');
INSERT INTO categorys values('category2');

INSERT INTO users values('Vasya', 'root', 1);
INSERT INTO users values('Ivsn', '123', 3);

INSERT INTO items values('Zayavka1', '2013-10-27 02:00:00', 1, 1, 1);
INSERT INTO items values('Zayavka1', '2013-10-27 02:00:00', 2, 2, 2);

INSERT INTO comments values('Zayavka nomer 1', '2013-10-27 02:00:00', 1);
INSERT INTO comments values('Zayavka nomer 2', '2013-10-27 02:00:00', 2);

INSERT INTO attach values('filename1', 1);
INSERT INTO attach values('filename2', 2);