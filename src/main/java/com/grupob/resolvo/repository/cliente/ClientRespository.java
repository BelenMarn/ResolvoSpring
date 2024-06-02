package com.grupob.resolvo.repository.cliente;

import com.grupob.resolvo.model.cliente.Client;
import com.grupob.resolvo.model.exception.EmptyClientList;

import java.util.List;

public interface ClientRespository {
    List<Client> findAllClients() throws EmptyClientList;
}
