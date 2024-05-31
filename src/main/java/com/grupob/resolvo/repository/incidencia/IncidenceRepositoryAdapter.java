package com.grupob.resolvo.repository.incidencia;

import com.grupob.resolvo.model.incidencia.Incidence;
import com.grupob.resolvo.model.enums.Status;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;
import com.grupob.resolvo.model.exception.NoIncidenceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IncidenceRepositoryAdapter implements IncidenceRepository {

    //BELÉN
    private final String SELECT_INCIDENCE_OF_TECHNICIAN  = "SELECT idIncidencia, idTrabajador, i.idCliente, " +
                                                        "CONCAT(cliente.nombre,\" \", cliente.apellidos) as nombreCliente, " +
                                                        "dispositivo, marca, modelo, ubicacion, motivo, fechaAltaIncidencia, " +
                                                        "estado, informeTecnico, fechaCierreIncidencia, firmaDigital, media " +
                                                        "FROM incidencia i, cliente " +
                                                        "WHERE i.idCliente = cliente.idCliente " +
                                                        "AND idTrabajador = ?";

    private final String UPDATE_FROM_PHONE = "UPDATE incidencia SET estado = ?, informeTecnico = ?, fechaCierreIncidencia = ?, firmaDigital = ? " +
                                            "WHERE idIncidencia = ?";

    private final String UPDATE_STATUS = "UPDATE incidencia SET estado = ? WHERE idIncidencia = ?";

    //MARCOS:
    private final String SELECT_INCIDENCE_BY_ID = "SELECT idIncidencia, Incidencia.idCliente, CONCAT(Cliente.nombre,\" \", Cliente.apellidos) as nombreCliente, " +
                                                "Incidencia.idTrabajador,CONCAT(Trabajador.nombre,\" \", Trabajador.apellidos) as nombreTrabajador, " +
                                                "dispositivo, marca, modelo, ubicacion, motivo, fechaAltaIncidencia, " +
                                                "estado, informeTecnico, fechaCierreIncidencia, firmaDigital, media " +
                                                "FROM Incidencia, Cliente, Trabajador " +
                                                "WHERE Incidencia.idCliente = Cliente.idCliente " +
                                                "AND Incidencia.idTrabajador = Trabajador.idTrabajador " +
                                                "AND Incidencia.idIncidencia = ?";

    private final String SELECT_ALL_INCIDENCES = "SELECT idIncidencia, Incidencia.idCliente,CONCAT(Cliente.nombre,\" \", Cliente.apellidos) " +
                                            "as nombreCliente, Incidencia.idTrabajador,CONCAT(Trabajador.nombre,\" \", Trabajador.apellidos) " +
                                            "as nombreTrabajador, dispositivo, marca, modelo, ubicacion, motivo, fechaAltaIncidencia, estado, " +
                                            "informeTecnico, fechaCierreIncidencia, firmaDigital, media " +
                                            "FROM Incidencia, Cliente, Trabajador " +
                                            "WHERE Incidencia.idCliente = Cliente.idCliente " +
                                            "AND Incidencia.idTrabajador = Trabajador.idTrabajador";


    private JdbcTemplate jdbcTemplate;
    public IncidenceRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //BELEN
    @Override
    public List<Incidence> findIncidencesOfTechnician(int id) throws EmptyIncidenceList {

        RowMapper<Incidence> mapper = (rs, rowNum) -> {
            Incidence incidence = new Incidence();
            incidence.setId_incidence(rs.getInt("idIncidencia"));
            incidence.setId_worker(rs.getInt("idTrabajador"));
            incidence.setWorkerName("");
            incidence.setId_client(rs.getInt("idCliente"));
            incidence.setClientName(rs.getString("nombreCliente"));
            incidence.setDevice(rs.getString("dispositivo"));
            incidence.setBrand(rs.getString("marca"));
            incidence.setModel(rs.getString("modelo"));
            incidence.setLocation(rs.getString("ubicacion"));
            incidence.setReason(rs.getString("motivo"));
            incidence.setOpen_date(rs.getTimestamp("fechaAltaIncidencia").toLocalDateTime());
            incidence.setTechnical_report(rs.getString("informeTecnico") == null ? null : rs.getString("informeTecnico"));
            incidence.setClose_date(rs.getTimestamp("fechaCierreIncidencia") == null ? null :  rs.getTimestamp("fechaCierreIncidencia").toLocalDateTime());
            incidence.setDigital_sign(rs.getBytes("firmaDigital") == null ? null : rs.getBytes("firmaDigital"));
            incidence.setStatus(Status.fromString(rs.getString("estado")));
            incidence.setMedia(rs.getBytes("media"));

            return incidence;
        };
        List<Incidence> incidences = jdbcTemplate.query(SELECT_INCIDENCE_OF_TECHNICIAN, mapper, id);

        if (incidences.isEmpty()) {
            throw new EmptyIncidenceList("No incidences found");
        } else {
            return incidences;
        }
    }

    //BELÉN
    @Override
    public boolean updateIncidenceFromPhone(int id, Incidence incidence) {
        try {
            int rowsAffected = jdbcTemplate.update(UPDATE_FROM_PHONE,
                    incidence.getStatus().toString(),
                    incidence.getTechnical_report(),
                    incidence.getClose_date(),
                    incidence.getDigital_sign(),
                    id);

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating incidence from phone: " + id, e);
        }
    }

    @Override
    public boolean updateStatus(int id, String status) {
        Object[] args = new Object[]{status, id};

        int rowsAffected = jdbcTemplate.update(UPDATE_STATUS, args);
        return rowsAffected > 0;
    }


    //MARCOS
    @Override
    public Incidence findIncidenceById(int id) throws NoIncidenceFoundException {
        Incidence incidence = jdbcTemplate.queryForObject(SELECT_INCIDENCE_BY_ID, Incidence.class, id);

        if(incidence != null){
            return incidence;
        }else{
            throw new NoIncidenceFoundException("No incidence found");
        }
    }

    //MARCOS
    @Override
    public List<Incidence> findAllIncidences() throws EmptyIncidenceList {
        RowMapper<Incidence> mapper = (rs, rowNum) -> {
            Incidence incidence = new Incidence();
            incidence.setId_incidence(rs.getInt("idIncidencia"));
            incidence.setId_client(rs.getInt("Incidencia.idCliente"));
            incidence.setClientName(rs.getString("nombreCliente"));
            incidence.setId_worker(rs.getInt("Incidencia.idTrabajador"));
            incidence.setWorkerName(rs.getString("nombreTrabajador"));
            incidence.setDevice(rs.getString("dispositivo"));
            incidence.setBrand(rs.getString("marca"));
            incidence.setModel(rs.getString("modelo"));
            incidence.setLocation(rs.getString("ubicacion"));
            incidence.setReason(rs.getString("motivo"));
            incidence.setOpen_date(rs.getTimestamp("fechaAltaIncidencia").toLocalDateTime());
            incidence.setTechnical_report(rs.getString("informeTecnico") == null ? null : rs.getString("informeTecnico"));
            incidence.setClose_date(rs.getTimestamp("fechaCierreIncidencia") == null ? null :  rs.getTimestamp("fechaCierreIncidencia").toLocalDateTime());
            incidence.setDigital_sign(rs.getBytes("firmaDigital") == null ? null : rs.getBytes("firmaDigital"));
            incidence.setStatus(Status.fromString(rs.getString("estado")));
            incidence.setMedia(rs.getBytes("media"));


            return incidence;
        };
        List<Incidence> incidences = jdbcTemplate.query(SELECT_ALL_INCIDENCES, mapper);

        if (incidences.isEmpty()) {
            throw new EmptyIncidenceList("No incidences found");
        } else {
            return incidences;
        }
    }


}
