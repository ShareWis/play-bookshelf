# --- !Ups

CREATE TABLE IF NOT EXISTS author (
  id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS book (
  id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  author_id BIGINT(20) NOT NULL
);

INSERT INTO author VALUES (1, "Martin Odersky");
INSERT INTO book VALUES (1, "Programming in Scala", 1);

# --- !Downs

DROP TABLE book;
DROP TABLE author;
