DROP TABLE IF EXISTS liquibase.books;
CREATE TABLE BOOKS (
  book_id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(60) NOT NULL,
  published INTEGER (4) NOT NULL,
  image VARCHAR(120) NOT NULL,
  description VARCHAR(1200) NOT NULL,
  available BIT NOT NULL default true
);
