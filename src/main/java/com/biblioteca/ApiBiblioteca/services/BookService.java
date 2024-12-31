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

    public String upBook(String isbn, Book book) {
        var id = UUID.fromString(isbn);
        var bookEntity = bookRepository.findById(id);

        if(bookEntity.isPresent()) {
            var existingBook = bookEntity.get();

            if (book.getNameBook() != null) {
                existingBook.setNameBook(book.getNameBook());
            }
            if (book.getSection() != null) {
                existingBook.setSection(book.getSection());
            }
            if (book.getAuthor() != null) {
                existingBook.setAuthor(book.getAuthor());
            }
            if (book.getAuthor() != null) {
                existingBook.setAuthor(book.getAuthor());
            }
            if (book.getPublisher() != null) {
                existingBook.setPublisher(book.getPublisher());
            }

            bookRepository.save(existingBook);

            return "Book atualizado com sucesso!";
        }

        return "Book não encontrado!";
    }

    public String deleteBook(String isbn) {
        var id = UUID.fromString(isbn);
        var bookExists = bookRepository.existsById(id);

        if(bookExists) {
            bookRepository.deleteById(id);
            return "Livro deletado com sucesso!";
        }

        return "Livro não encontrado";
    }
}
