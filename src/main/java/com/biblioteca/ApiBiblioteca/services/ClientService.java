package com.biblioteca.ApiBiblioteca.services;

import com.biblioteca.ApiBiblioteca.DTO.CreateClientDTO;
import com.biblioteca.ApiBiblioteca.entity.Client;
import com.biblioteca.ApiBiblioteca.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public UUID createClient(CreateClientDTO createClientDTO) {
        Client client = new Client();

        client.setName(createClientDTO.name());
        client.setCpf(createClientDTO.cpf());
        client.setCep(createClientDTO.cep());
        client.setNumTel(createClientDTO.numTel());
        client.setDataNas(createClientDTO.dataNas());

        clientRepository.save(client);

        return client.getClientId();
    }

    public Optional<Client> getClientById(String clientId) {
        return clientRepository.findById(UUID.fromString(clientId));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public String upClient(String clientId, Client client) {
        var id = UUID.fromString(clientId);
        var clientEntity = clientRepository.findById(id);

        if(clientEntity.isPresent()) {
            var existingClient = clientEntity.get();

            if (client.getName() != null) {
                existingClient.setName(client.getName());
            }
            if (client.getCpf() != null) {
                existingClient.setCpf(client.getCpf());
            }
            if (client.getCep() != null) {
                existingClient.setCep(client.getCep());
            }
            if (client.getNumTel() != null) {
                existingClient.setNumTel(client.getNumTel());
            }
            if (client.getDataNas() != null) {
                existingClient.setDataNas(client.getDataNas());
            }

            clientRepository.save(existingClient);

            return "Client atualizado com sucesso!";
        }

        return "Client não encontrado!";
    }

    public String delete(String clientId) {
        var id = UUID.fromString(clientId);
        var clientExists = clientRepository.existsById(id);

        if(clientExists) {
            clientRepository.deleteById(id);
            return "Client deletado com sucesso!";
        }

        return "Client não encontrado";
    }
}
