DROP TABLE IF EXISTS liquibase.authors;
CREATE TABLE AUTHORS (
  author_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  surname VARCHAR(60) NOT NULL
);
