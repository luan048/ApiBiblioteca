package com.biblioteca.ApiBiblioteca.repository;

import com.biblioteca.ApiBiblioteca.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
