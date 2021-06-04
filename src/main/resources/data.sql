INSERT INTO User_Detail (username, password, role, is_account_non_expired, is_account_non_locked, is_credential_non_expired, is_enabled) VALUES
    ('admin', '$2a$10$SjuXJLOgVh6yGc9tnSSP6OgfJD86DZX8x84aE/QBGxLOKzTEPrGd6', 'ADMIN', true, true, true, true);

INSERT INTO School (id, name, address, phone_number) VALUES
    (1, 'Seoul University', 'in Seoul', '02-000-0000');

INSERT INTO Student (name, address, phone_number, grade, fcm_token, school_id) VALUES
    ('Kim Taeyeon', 'in Seoul', '02-000-0000', 'U_FIRST', '', 1),
    ('Ikeda Elaiza', 'in Seoul', '02-000-0000', 'U_SECOND', '', 1);

INSERT INTO Teacher (name, address, phone_number, fcm_token, school_id) VALUES
    ('Choi Yoojung', 'in Seoul', '02-000-0000', '', 1),
    ('Karina', 'in Seoul', '02-000-0000', '', 1);