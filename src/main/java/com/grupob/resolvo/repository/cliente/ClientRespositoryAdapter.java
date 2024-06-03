package com.grupob.resolvo.repository.cliente;

import com.grupob.resolvo.model.cliente.Client;
import com.grupob.resolvo.model.enums.Contract;
import com.grupob.resolvo.model.exception.EmptyClientList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ClientRespositoryAdapter implements ClientRespository {

    private final String SELECT_ALL_CLIENTS = "SELECT idCliente, nombre, apellidos, calle, codPostal, " +
                                         "ciudad, provincia, telefono, dni, email, contrato, numIncidencias " +
                                        "FROM cliente";

    private final String SELECT_BY_EMAIL = "SELECT idCliente, nombre, apellidos, calle, codPostal, " +
                                        "ciudad, provincia, telefono, dni, email, contrato, numIncidencias " +
                                        "FROM cliente WHERE email = ?";

    private final String CHECK_EMAIL = "SELECT COUNT(*) FROM cliente WHERE email = ?";

    private final String INSERT_CLIENT = "INSERT INTO cliente(nombre, apellidos, calle, codPostal, ciudad, provincia, telefono, dni, email) " +
                                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";


    private JdbcTemplate jdbcTemplate;
    public ClientRespositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Client> findAllClients() throws EmptyClientList {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_CLIENTS);

        List<Client> clients = new ArrayList<>();
        RowMapper<Client> mapper = (rs, rowNum) -> {
            Client client = new Client();

            client.setId_client((int) ((Long) rs.getObject("idCliente")).longValue());
            client.setName(rs.getString("nombre"));
            client.setSurname(rs.getString("apellidos"));
            client.setStreet(rs.getString("calle"));
            client.setPostal_code(rs.getString("codPostal"));
            client.setCity(rs.getString("ciudad"));
            client.setProvince(rs.getString("provincia"));
            client.setPhone(rs.getString("telefono"));
            client.setDni(rs.getString("dni"));
            client.setEmail(rs.getString("email"));

            String contractString = rs.getString("contrato");
            client.setContract(Contract.fromString(contractString));
            client.setNum_incidents(rs.getInt("numIncidencias"));
            return client;
        };

        clients = jdbcTemplate.query(SELECT_ALL_CLIENTS, mapper);

        if (clients.isEmpty()) {
            throw new EmptyClientList("Empty client list");
        } else {
            return clients;
        }
    }

    @Override
    public Client findClientByEmail(String email) {
        String[] args = { email };

        return jdbcTemplate.queryForObject(
                SELECT_BY_EMAIL,
                args,
                (rs, rowNum) -> {
                    Client client = new Client();

                    client.setId_client(rs.getInt("idCliente"));
                    client.setName(rs.getString("nombre"));
                    client.setSurname(rs.getString("apellidos"));
                    client.setStreet(rs.getString("calle"));
                    client.setPostal_code(rs.getString("codPostal"));
                    client.setCity(rs.getString("ciudad"));
                    client.setProvince(rs.getString("provincia"));
                    client.setPhone(rs.getString("telefono"));
                    client.setDni(rs.getString("dni"));
                    client.setEmail(rs.getString("email"));

                    String contractString = rs.getString("contrato");
                    client.setContract(Contract.fromString(contractString));
                    client.setNum_incidents(rs.getInt("numIncidencias"));
                    return client;
                }
        );
    }

    @Override
    public boolean checkEmail(String email) {
        int rows = jdbcTemplate.queryForObject(CHECK_EMAIL, new Object[]{email}, Integer.class);
        return rows > 0;
    }

    @Override
    public void addClient(Client client) {
        try {
            jdbcTemplate.update(INSERT_CLIENT,
                    client.getName(),
                    client.getSurname(),
                    client.getStreet(),
                    client.getPostal_code(),
                    client.getCity(),
                    client.getProvince(),
                    client.getPhone(),
                    client.getDni(),
                    client.getEmail()
            );

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

}
