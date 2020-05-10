insert into book (id, author, title, borrowed_till) values (nextval('book_seq'), 'Kaczanowski', 'Testy', null);
insert into book (id, author, title, borrowed_till) values (nextval('book_seq'), 'Rowling', 'Harry Potter', null);
insert into book (id, author, title, borrowed_till) values (nextval('book_seq'), 'Tolkien', 'Wladca pierscieni', null);
insert into book (id, author, title, borrowed_till) values (nextval('book_seq'), 'Bloch', 'Effective Java', null);
insert into book (id, author, title, borrowed_till) values (nextval('book_seq'), 'Dostojewski', 'Zbrodnia i kara', null);

commit;
