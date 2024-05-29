package com.grupob.resolvo.repository;

import com.grupob.resolvo.model.Incidence;
import com.grupob.resolvo.model.Media;
import com.grupob.resolvo.model.enums.Status;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;
import com.grupob.resolvo.model.exception.NoIncidenceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class IncidenceRepositoryAdapter implements IncidenceRepository {

    private final String SELECT_INCIDENCE_OF_TECHNICIAN = "SELECT i.idIncidencia, i.idTrabajador, i.dispositivo, i.marca, i.modelo, " +
                                                        "i.ubicacion, i.motivo, i.fechaAltaIncidencia, i.estado, i.informeTecnico, " +
                                                        "i.fechaCierreIncidencia, i.firmaDigital, m.idMedia, m.data " +
                                                        "FROM incidencia i, media m " +
                                                        "WHERE i.idIncidencia = m.idIncidencia " +
                                                        "AND i.idTrabajador = ?";

    private final String SELECT_INCIDENCE_BY_ID = "SELECT idIncidencia, Incidencia.idCliente, CONCAT(Cliente.nombre,\" \", Cliente.apellidos) as nombreCliente, " +
                                                "Incidencia.idTrabajador,CONCAT(Trabajador.nombre,\" \", Trabajador.apellidos) as nombreTrabajador, " +
                                                "dispositivo, marca, modelo, ubicacion, motivo, fechaAltaIncidencia, " +
                                                "estado, informeTecnico, fechaCierreIncidencia, firmaDigital " +
                                                "FROM Incidencia, Cliente, Trabajador " +
                                                "WHERE Incidencia.idCliente = Cliente.idCliente " +
                                                "AND Incidencia.idTrabajador = Trabajador.idTrabajador " +
                                                "AND Incidencia.idIncidencia = ?";

    private final String SELECT_ALL_INCIDENCES = "SELECT idIncidencia, Incidencia.idCliente,CONCAT(Cliente.nombre,\" \", Cliente.apellidos) " +
                                            "as nombreCliente, Incidencia.idTrabajador,CONCAT(Trabajador.nombre,\" \", Trabajador.apellidos) " +
                                            "as nombreTrabajador, dispositivo, marca, modelo, ubicacion, motivo, fechaAltaIncidencia, estado, informeTecnico, " +
                                            "fechaCierreIncidencia, firmaDigital" +
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

        try{
            RowMapper<Incidence> mapper = (rs, rowNum) -> {
                Incidence incidence = new Incidence();
                incidence.setId_incidence(rs.getInt("i.idIncidencia"));
                incidence.setId_client(0);
                incidence.setClientName("");
                incidence.setId_worker(rs.getInt("i.idTrabajador"));
                incidence.setWorkerName("");
                incidence.setDevice(rs.getString("i.dispositivo"));
                incidence.setBrand(rs.getString("i.marca"));
                incidence.setModel(rs.getString("i.modelo"));
                incidence.setLocation(rs.getString("i.ubicacion"));
                incidence.setReason(rs.getString("i.motivo"));
                incidence.setOpen_date(rs.getTimestamp("i.fechaAltaIncidencia").toLocalDateTime());
                incidence.setTechnical_report(rs.getString("i.informeTecnico") == null ? null : rs.getString("i.informeTecnico"));
                incidence.setClose_date(rs.getTimestamp("i.fechaCierreIncidencia") == null ? null :  rs.getTimestamp("i.fechaCierreIncidencia").toLocalDateTime());
                incidence.setDigital_sign(rs.getBytes("i.firmaDigital") == null ? null : rs.getBytes("i.firmaDigital"));
                incidence.setStatus(Status.fromString(rs.getString("i.estado")));

                ArrayList<Media> mediaList = new ArrayList<>();
                while (rs.next()) {
                    int idMedia = rs.getInt("m.idMedia");
                    byte[] mediaData = rs.getBytes("m.data");
                    if (mediaData != null) {
                        mediaList.add(new Media(idMedia, incidence.getId_incidence(), mediaData));
                    }
                }
                incidence.setMedia(mediaList);

                return incidence;
            };
            List<Incidence> incidences = jdbcTemplate.query(SELECT_INCIDENCE_OF_TECHNICIAN, mapper, id);

            if (incidences.isEmpty()) {
                throw new EmptyIncidenceList("No incidences found");
            } else {
                return incidences;
            }
        } catch (Exception e) {
            // Handle the exception appropriately
            e.printStackTrace(); // Log the exception for debugging
            throw new RuntimeException("Error finding incidences for technician: " + id, e); // Re-throw a more specific exception
        }

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

            incidence.setMedia(null);

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
