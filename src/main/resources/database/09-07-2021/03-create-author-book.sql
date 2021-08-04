DROP TABLE IF EXISTS liquibase.author_book;
CREATE TABLE AUTHOR_BOOK(
    author_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES Authors(author_id) ON DELETE CASCADE ,
    FOREIGN KEY (book_id) REFERENCES Books(book_id) ON DELETE CASCADE
);
