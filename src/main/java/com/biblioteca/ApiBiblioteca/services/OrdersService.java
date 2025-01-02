package com.biblioteca.ApiBiblioteca.services;

import com.biblioteca.ApiBiblioteca.DTO.CreateOrderDTO;
import com.biblioteca.ApiBiblioteca.entity.Orders;
import com.biblioteca.ApiBiblioteca.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {
    private OrderRepository orderRepository;

    public OrdersService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public UUID createOrder(CreateOrderDTO createOrderDTO) {
        Orders order = new Orders();

        order.setBook(createOrderDTO.book());
        order.setClient(createOrderDTO.client());
        order.setCreationTimestamp(createOrderDTO.creationTimestamp());
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

            if(orders.getCreationTimestamp() != null) {
                order.setCreationTimestamp(orders.getCreationTimestamp());
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
