package com.biblioteca.ApiBiblioteca.repository;

import com.biblioteca.ApiBiblioteca.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID> {
}
