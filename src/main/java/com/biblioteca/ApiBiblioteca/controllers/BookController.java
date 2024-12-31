package com.biblioteca.ApiBiblioteca.controllers;

import com.biblioteca.ApiBiblioteca.DTO.CreateBookDTO;
import com.biblioteca.ApiBiblioteca.entity.Book;
import com.biblioteca.ApiBiblioteca.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> post(@RequestBody CreateBookDTO createBookDTO) {
        var isbn = bookService.registerBook(createBookDTO);

        return ResponseEntity.created(URI.create("/v1/createBook" + isbn.toString())).build();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getByIsbn(@PathVariable("isbn") String id) {
        var book = bookService.getBookByIsbn(id);

        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        var books = bookService.getAllBooks();

        return ResponseEntity.ok(books);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Void> upBook(@PathVariable("isbn") String isbn, @RequestBody Book book) {
        bookService.upBook(isbn, book);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteById(@PathVariable("isbn") String id) {
        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }
}
