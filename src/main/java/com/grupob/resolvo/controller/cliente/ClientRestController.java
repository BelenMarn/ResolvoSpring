package com.grupob.resolvo.controller.cliente;

import com.grupob.resolvo.model.cliente.Client;
import com.grupob.resolvo.model.exception.EmptyClientList;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;
import com.grupob.resolvo.model.exception.NoClientUserFoundException;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.incidencia.Incidence;
import com.grupob.resolvo.model.trabajador.WorkerData;
import com.grupob.resolvo.model.usuario_interno.InternUser;
import com.grupob.resolvo.services.cliente.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //SERGIO:
    @GetMapping("/findByEmail")
    public Client findClientByEmail(@RequestParam("email") String email) throws NoClientUserFoundException {
        final Client client = clientService.findClientByEmail(email);

        if (client == null) {
            throw new NoClientUserFoundException("Client not found");
        } else {
            return client;
        }
    }

    @GetMapping("/checkEmail")
    public boolean checkEmail(@RequestParam("email") String email) {
        return clientService.checkEmail(email);
    }

    @PostMapping("/add")
    public void addClient(@RequestBody Client client) {
        clientService.addClient(client);
    }

}
