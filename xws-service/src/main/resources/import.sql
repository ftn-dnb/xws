INSERT INTO `authorities` (id, name) VALUES (1, 'ROLE_AUTHOR');
INSERT INTO `authorities` (id, name) VALUES (2, 'ROLE_EDITOR');

INSERT INTO `users` (id, email, activated_account, first_name, last_name, last_password_reset_date, password, username) VALUES (1,'john@doe.com', true, 'John', 'Doe','2017-10-01 21:58:58', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'john.doe');
INSERT INTO `users` (id, email, activated_account, first_name, last_name, last_password_reset_date, password, username) VALUES (2,'jane@doe.com', true, 'Jane', 'Doe','2017-09-01 22:40:00', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'jane.doe');
INSERT INTO `users` (id, email, activated_account, first_name, last_name, last_password_reset_date, password, username) VALUES (3,'john@smith.com', true, 'John', 'Smith','2017-09-01 22:40:00', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'john.smith');
INSERT INTO `users` (id, email, activated_account, first_name, last_name, last_password_reset_date, password, username) VALUES (4,'mike@smith.com', true, 'Mike', 'Smith','2017-09-01 22:40:00', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'mike.smith');
INSERT INTO `users` (id, email, activated_account, first_name, last_name, last_password_reset_date, password, username) VALUES (5,'jess@doe.com', true, 'Jess', 'Doe','2017-09-01 22:40:00', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'jess.doe');

INSERT INTO `user_authority` (user_id, authority_id) VALUES (1, 1);
INSERT INTO `user_authority` (user_id, authority_id) VALUES (2, 2); -- editor
INSERT INTO `user_authority` (user_id, authority_id) VALUES (3, 1);
INSERT INTO `user_authority` (user_id, authority_id) VALUES (4, 1);
INSERT INTO `user_authority` (user_id, authority_id) VALUES (5, 2); -- editor
