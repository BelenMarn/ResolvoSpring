package com.grupob.resolvo.controller.cliente;

import com.grupob.resolvo.model.cliente.Client;
import com.grupob.resolvo.model.exception.EmptyClientList;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;
import com.grupob.resolvo.model.incidencia.Incidence;
import com.grupob.resolvo.services.cliente.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

    private ClientService clientService;
    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/findAll")
    public List<Client> findAllClients() throws EmptyClientList {
        final List<Client> clients = clientService.findAllClients();

        if (clients.isEmpty()) {
            throw new EmptyClientList("Empty client list");
        }else{
            return clients;
        }
    }
}
