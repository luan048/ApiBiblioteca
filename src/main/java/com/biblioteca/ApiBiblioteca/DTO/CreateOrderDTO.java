package com.biblioteca.ApiBiblioteca.DTO;

import com.biblioteca.ApiBiblioteca.entity.Book;
import com.biblioteca.ApiBiblioteca.entity.Client;

import java.util.UUID;

public record CreateOrderDTO(UUID bookId, UUID clientId, String return_date) {
}
