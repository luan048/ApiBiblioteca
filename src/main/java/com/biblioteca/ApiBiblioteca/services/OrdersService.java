package com.biblioteca.ApiBiblioteca.services;

import com.biblioteca.ApiBiblioteca.DTO.CreateOrderDTO;
import com.biblioteca.ApiBiblioteca.entity.Orders;
import com.biblioteca.ApiBiblioteca.repository.BookRepository;
import com.biblioteca.ApiBiblioteca.repository.ClientRepository;
import com.biblioteca.ApiBiblioteca.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {
    private OrderRepository orderRepository;

    private BookRepository bookRepository;
    private ClientRepository clientRepository;

    public OrdersService(OrderRepository orderRepository, BookRepository bookRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    public UUID createOrder(CreateOrderDTO createOrderDTO) {
        var book = bookRepository.findById(createOrderDTO.bookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        var client = clientRepository.findById(createOrderDTO.clientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        Orders order = new Orders();

        order.setBook(book);
        order.setClient(client);
        order.setReturn_date(createOrderDTO.return_date());

        orderRepository.save(order);

        return order.getOrderId();
    }

    public Optional<Orders> getOrderById(String orderId) {
        return orderRepository.findById(UUID.fromString(orderId));
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public String upOrder(String orderId, Orders orders) {
        var id = UUID.fromString(orderId);
        var orderEntity = orderRepository.findById(id);

        if(orderEntity.isPresent()) {
            var order = orderEntity.get();

            if(orders.getBook() != null) {
                order.setBook(orders.getBook());
            }

            if(orders.getClient() != null) {
                order.setClient(orders.getClient());
            }

            if(orders.getReturn_date() != null) {
                order.setReturn_date(orders.getReturn_date());
            }

            orderRepository.save(order);

            return "Pedido atualizado com sucesso!";
        }

        return "Pedido não encontrado";
    }

    public String deleteById(String orderId) {
        var id = UUID.fromString(orderId);
        var orderEntity = orderRepository.existsById(id);

        if(orderEntity) {
            orderRepository.deleteById(id);

            return "Pedido deletado com sucesso!";
        }

        return "Pedido não encontrado!";
    }
}
