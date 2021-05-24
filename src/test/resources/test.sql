INSERT INTO User_Detail (id, username, password, role, is_account_non_expired, is_account_non_locked, is_credential_non_expired, is_enabled)
 VALUES (1, 'user', '$2a$10$SjuXJLOgVh6yGc9tnSSP6OgfJD86DZX8x84aE/QBGxLOKzTEPrGd6', 'ADMIN', true, true, true, true);

INSERT INTO School (id, name, address, phone_number) VALUES (1, 'Seoul University', 'in Seoul', '02-000-0000');
INSERT INTO School (id, name, address, phone_number) VALUES (2, 'Busan University', 'in BUsan', '051-000-0000');
INSERT INTO School (id, name, address, phone_number) VALUES (3, 'JeonBuk University', 'in Iksan', '063-000-0000');

INSERT INTO Student (id, name, address, phone_number, grade, fcm_token, school_id) VALUES (1, 'Student A', 'in Seoul', '02-000-0000', 'U_FIRST', '', 1);
INSERT INTO Student (id, name, address, phone_number, grade, fcm_token, school_id) VALUES (2, 'Student B', 'in Seoul', '02-000-0000', 'U_FIRST', '', 1);
INSERT INTO Student (id, name, address, phone_number, grade, fcm_token, school_id) VALUES (3, 'Student C', 'in Seoul', '02-000-0000', 'U_FIRST', '', 1);
INSERT INTO Student (id, name, address, phone_number, grade, fcm_token, school_id) VALUES (4, 'Student D', 'in Busan', '051-000-0000', 'U_FIRST', '', 2);
INSERT INTO Student (id, name, address, phone_number, grade, fcm_token, school_id) VALUES (5, 'Student E', 'in Busan', '051-000-0000', 'U_FIRST', '', 2);
INSERT INTO Student (id, name, address, phone_number, grade, fcm_token, school_id) VALUES (6, 'Student A', 'in Iksan', '063-000-0000', 'U_FIRST', '', 3);
INSERT INTO Student (id, name, address, phone_number, grade, fcm_token, school_id) VALUES (7, 'Student G', 'in Iksan', '063-000-0000', 'U_FIRST', '', 3);

INSERT INTO Teacher (id, name, address, phone_number, fcm_token, school_id) VALUES (1, 'Teacher A', 'in Seoul', '02-000-0000', '', 1);
INSERT INTO Teacher (id, name, address, phone_number, fcm_token, school_id) VALUES (2, 'Teacher B', 'in Seoul', '02-000-0000', '', 1);
INSERT INTO Teacher (id, name, address, phone_number, fcm_token, school_id) VALUES (3, 'Teacher C', 'in Seoul', '02-000-0000', '', 1);
INSERT INTO Teacher (id, name, address, phone_number, fcm_token, school_id) VALUES (4, 'Teacher D', 'in Busan', '051-000-0000', '', 2);
INSERT INTO Teacher (id, name, address, phone_number, fcm_token, school_id) VALUES (5, 'Teacher E', 'in Busan', '051-000-0000', '', 2);
INSERT INTO Teacher (id, name, address, phone_number, fcm_token, school_id) VALUES (6, 'Teacher F', 'in Iksan', '063-000-0000', '', 3);
INSERT INTO Teacher (id, name, address, phone_number, fcm_token, school_id) VALUES (7, 'Teacher G', 'in Iksan', '063-000-0000', '', 3);