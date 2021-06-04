INSERT INTO User_Detail (username, password, role, is_account_non_expired, is_account_non_locked, is_credential_non_expired, is_enabled) VALUES
    ('user', '$2a$10$SjuXJLOgVh6yGc9tnSSP6OgfJD86DZX8x84aE/QBGxLOKzTEPrGd6', 'ADMIN', true, true, true, true);

INSERT INTO School (id, name, address, phone_number) VALUES
    (2, 'Busan University', 'in BUsan', '051-000-0000'),
    (3, 'JeonBuk University', 'in Iksan', '063-000-0000');

INSERT INTO Student (name, address, phone_number, grade, fcm_token, school_id) VALUES
    ('Student A', 'in Seoul', '02-000-0000', 'U_FIRST', '', 1),
    ('Student B', 'in Seoul', '02-000-0000', 'U_FIRST', '', 1),
    ('Student C', 'in Seoul', '02-000-0000', 'U_FIRST', '', 1),
    ('Student D', 'in Busan', '051-000-0000', 'U_FIRST', '', 2),
    ('Student E', 'in Busan', '051-000-0000', 'U_FIRST', '', 2),
    ('Student A', 'in Iksan', '063-000-0000', 'U_FIRST', '', 3),
    ('Student G', 'in Iksan', '063-000-0000', 'U_FIRST', '', 3);

INSERT INTO Teacher (name, address, phone_number, fcm_token, school_id) VALUES
    ('Teacher A', 'in Seoul', '02-000-0000', '', 1),
    ('Teacher B', 'in Seoul', '02-000-0000', '', 1),
    ('Teacher C', 'in Seoul', '02-000-0000', '', 1),
    ('Teacher D', 'in Busan', '051-000-0000', '', 2),
    ('Teacher E', 'in Busan', '051-000-0000', '', 2),
    ('Teacher F', 'in Iksan', '063-000-0000', '', 3),
    ('Teacher G', 'in Iksan', '063-000-0000', '', 3);