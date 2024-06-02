package com.grupob.resolvo.services.cliente;

import com.grupob.resolvo.model.cliente.Client;
import com.grupob.resolvo.model.exception.EmptyClientList;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;
import com.grupob.resolvo.model.incidencia.Incidence;
import com.grupob.resolvo.repository.cliente.ClientRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ClientService {
    private ClientRespository clientRespository;
    @Autowired
    public ClientService(ClientRespository clientRespository) {
        this.clientRespository = clientRespository;
    }

    public List<Client> findAllClients() throws EmptyClientList {
        final List<Client> clients = new ArrayList<Client>();
        final Iterator<Client> clientIterator = clientRespository.findAllClients().iterator();
        while (clientIterator.hasNext()) {
            clients.add(clientIterator.next());
        }

        if(!clients.isEmpty()){
            return clients;
        }else{
            throw new EmptyClientList("Empty client list");
        }
    }
}
