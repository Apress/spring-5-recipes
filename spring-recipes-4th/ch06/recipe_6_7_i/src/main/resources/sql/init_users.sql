insert into users (username, password, enabled) values ('user1', 'user1', true);
insert into users (username, password, enabled) values ('user2', 'user2', true);
insert into users (username, password, enabled) values ('user3', 'user3', true);

insert into authorities (username, authority) values('user1', 'ROLE_USER');
insert into authorities (username, authority) values('user2', 'ROLE_USER');
insert into authorities (username, authority) values('user3', 'ROLE_USER');