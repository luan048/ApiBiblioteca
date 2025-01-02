package com.biblioteca.ApiBiblioteca.controllers;

import com.biblioteca.ApiBiblioteca.DTO.CreateOrderDTO;
import com.biblioteca.ApiBiblioteca.entity.Orders;
import com.biblioteca.ApiBiblioteca.services.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrdersService ordersService;

    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    public ResponseEntity<Orders> post(@RequestBody CreateOrderDTO createOrderDTO) {
        var orderId = ordersService.createOrder(createOrderDTO);

        return ResponseEntity.created(URI.create("/v1/createOrd" + orderId.toString())).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getById(@PathVariable("orderId") String orderId) {
        var order = ordersService.getOrderById(orderId);

        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        var order = ordersService.getAllOrders();

        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> upOrder(@PathVariable("order_id") String orderId, @RequestBody Orders orders) {
        ordersService.upOrder(orderId, orders);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteById(@PathVariable("orderId") String orderId) {
        ordersService.deleteById(orderId);

        return ResponseEntity.noContent().build();
    }
}
