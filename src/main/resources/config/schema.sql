drop table user if exists;

CREATE TABLE user (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(512) UNIQUE,
  password VARCHAR(255)    DEFAULT ''
);


