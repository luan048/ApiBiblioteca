package com.biblioteca.ApiBiblioteca.DTO;

import com.biblioteca.ApiBiblioteca.entity.Book;
import com.biblioteca.ApiBiblioteca.entity.Client;

import java.time.Instant;

public record CreateOrderDTO(Book book, Client client, Instant creationTimestamp, String return_date) {
}
