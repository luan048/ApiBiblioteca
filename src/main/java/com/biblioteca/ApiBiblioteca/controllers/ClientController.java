package com.biblioteca.ApiBiblioteca.controllers;

import com.biblioteca.ApiBiblioteca.DTO.CreateClientDTO;
import com.biblioteca.ApiBiblioteca.entity.Client;
import com.biblioteca.ApiBiblioteca.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> post(@RequestBody CreateClientDTO createClientDTO) {
        var clientId = clientService.createClient(createClientDTO);

        return ResponseEntity.created(URI.create("/v1/createClient" + clientId.toString())).build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getById(@PathVariable("clientId") String clientId) {
        var client = clientService.getClientById(clientId);

        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        var clients = clientService.getAllClients();

        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Void> upClient(@PathVariable("clientId") String clientId, @RequestBody Client client) {
        clientService.upClient(clientId, client);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable("clientId") String clientId) {
        clientService.delete(clientId);
        return ResponseEntity.noContent().build();
    }
}
