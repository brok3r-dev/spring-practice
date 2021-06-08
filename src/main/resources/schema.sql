CREATE TABLE IF NOT EXISTS user_detail (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    is_account_non_expired BIT NOT NULL,
    is_account_non_locked BIT NOT NULL,
    is_credential_non_expired BIT NOT NULL,
    is_enabled BIT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS school (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS student (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    grade VARCHAR(255) NOT NULL,
    fcm_token VARCHAR(255) NOT NULL,
    school_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (school_id) REFERENCES school(id)
);

CREATE TABLE IF NOT EXISTS teacher (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    fcm_token VARCHAR(255) NOT NULL,
    school_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (school_id) REFERENCES school(id)
);