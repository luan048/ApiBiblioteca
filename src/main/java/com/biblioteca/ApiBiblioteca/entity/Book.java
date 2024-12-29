package com.biblioteca.ApiBiblioteca.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID isbn;

    @Column(name = "nameBook")
    private String nameBook;

    @Column(name = "section")
    private String section;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    public Book() {

    }

    public Book(UUID isbn, String nameBook, String section, String author, String publisher) {
        this.isbn = isbn;
        this.nameBook = nameBook;
        this.section = section;
        this.author = author;
        this.publisher = publisher;
    }

    public UUID getIsbn() {
        return isbn;
    }

    public void setIsbn(UUID isbn) {
        this.isbn = isbn;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
