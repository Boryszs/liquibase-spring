CREATE TABLE AUTHOR_BOOK(
    author_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES Authors(author_id),
    FOREIGN KEY (book_id) REFERENCES Books(book_id),
    UNIQUE (author_id, book_id)
);
