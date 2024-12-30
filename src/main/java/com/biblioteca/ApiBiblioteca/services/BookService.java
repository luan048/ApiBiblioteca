package com.biblioteca.ApiBiblioteca.services;

import com.biblioteca.ApiBiblioteca.DTO.CreateBookDTO;
import com.biblioteca.ApiBiblioteca.entity.Book;
import com.biblioteca.ApiBiblioteca.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public UUID registerBook(CreateBookDTO createBookDTO) {
        Book book = new Book();

        book.setNameBook(createBookDTO.nameBook());
        book.setSection(createBookDTO.section());
        book.setAuthor(createBookDTO.author());
        book.setPublisher(createBookDTO.publisher());

        bookRepository.save(book);

        return book.getIsbn();
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findById(UUID.fromString(isbn));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(String isbn) {
        var id = UUID.fromString(isbn);
        var bookExists = bookRepository.existsById(id);

        if(bookExists) {
            bookRepository.deleteById(id);
        }
    }
}
