CREATE TABLE IF NOT EXISTS my_user (
    id int(10) PRIMARY KEY,
    first_name VARCHAR(256) NOT NULL,
    last_name VARCHAR(256) NOT NULL,
    age int(10),
    CONSTRAINT uc_user_first_last_name UNIQUE(first_name, last_name),
    INDEX idx_user_id (id)
) engine=InnoDB;