package com.grupob.resolvo.repository.incidencia;

import com.grupob.resolvo.model.incidencia.Incidence;
import com.grupob.resolvo.model.enums.Status;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;
import com.grupob.resolvo.model.exception.NoIncidenceFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public class IncidenceRepositoryAdapter implements IncidenceRepository {
    private static Logger logger = LoggerFactory.getLogger(IncidenceRepositoryAdapter.class);

    //BELÉN
    private final String SELECT_INCIDENCE_OF_TECHNICIAN  = "SELECT idIncidencia, idTrabajador, i.idCliente, " +
                                                        "CONCAT(cliente.nombre,\" \", cliente.apellidos) as nombreCliente, " +
                                                        "dispositivo, marca, modelo, ubicacion, motivo, fechaAltaIncidencia, " +
                                                        "estado, informeTecnico, fechaCierreIncidencia, firmaDigital, fotografia " +
                                                        "FROM incidencia i, cliente " +
                                                        "WHERE i.idCliente = cliente.idCliente " +
                                                        "AND idTrabajador = ?";

    private final String UPDATE_FROM_PHONE = "UPDATE incidencia SET estado = ?, informeTecnico = ?, fechaCierreIncidencia = ?, firmaDigital = ? " +
                                            "WHERE idIncidencia = ?";

    private final String UPDATE_STATUS = "UPDATE incidencia SET estado = ? WHERE idIncidencia = ?";

    //MARCOS:
    private final String SELECT_INCIDENCE_BY_ID = "SELECT idIncidencia, I.idCliente, CONCAT(C.nombre, \" \", C.apellidos) as nombreCliente, I.idTrabajador, " +
                                                "CONCAT(T.nombre, \" \", T.apellidos) as nombreTrabajador, dispositivo, marca, modelo, ubicacion, " +
                                                "motivo, fechaAltaIncidencia, estado, informeTecnico, fechaCierreIncidencia, firmaDigital, fotografia " +
                                                "FROM Incidencia I LEFT JOIN Cliente C ON I.idCliente = C.idCliente " +
                                                "LEFT JOIN Trabajador T ON I.idTrabajador = T.idTrabajador " +
                                                "WHERE idIncidencia = ?";

    private final String SELECT_ALL_INCIDENCES = "SELECT idIncidencia, I.idCliente, CONCAT(C.nombre, \" \", C.apellidos) as nombreCliente, I.idTrabajador, " +
                                                "CONCAT(T.nombre, \" \", T.apellidos) as nombreTrabajador, dispositivo, marca, modelo, ubicacion, " +
                                                "motivo, fechaAltaIncidencia, estado, informeTecnico, fechaCierreIncidencia, firmaDigital, fotografia " +
                                                "FROM Incidencia I LEFT JOIN Cliente C ON I.idCliente = C.idCliente " +
                                                "LEFT JOIN Trabajador T ON I.idTrabajador = T.idTrabajador";

    private String UPDATE_WORKER = "UPDATE incidencia SET idTrabajador = ? WHERE idIncidencia = ?";


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
            incidence.setMedia(rs.getBytes("fotografia") == null ? null : rs.getBytes("fotografia"));

            return incidence;
        };
        List<Incidence> incidences = jdbcTemplate.query(SELECT_INCIDENCE_OF_TECHNICIAN, mapper, id);

        logger.info("Number of incidences retrieved: {}", incidences.size());

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
        String formattedQuery = SELECT_INCIDENCE_BY_ID + " " + id;
        logger.info("Executing query: {}", formattedQuery);

        RowMapper<Incidence> mapper = (rs, rowNum) -> {
            Incidence incidence = new Incidence();

            incidence.setId_incidence(rs.getInt("idIncidencia"));
            incidence.setId_client(rs.getInt("I.idCliente"));
            incidence.setClientName(rs.getString("nombreCliente"));
            incidence.setId_worker(rs.getInt("I.idTrabajador"));
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
            incidence.setMedia(rs.getBytes("fotografia") == null ? null : rs.getBytes("fotografia"));

            return incidence;
        };
        Incidence incidence = jdbcTemplate.queryForObject(SELECT_INCIDENCE_BY_ID, mapper, id);

        if (incidence != null) {
            return incidence;
        } else {
            throw new NoIncidenceFoundException("No incidence found");
        }
    }

    //MARCOS
    @Override
    public List<Incidence> findAllIncidences() throws EmptyIncidenceList {
        RowMapper<Incidence> mapper = (rs, rowNum) -> {
            Incidence incidence = new Incidence();
            incidence.setId_incidence(rs.getInt("idIncidencia"));
            incidence.setId_client(rs.getInt("I.idCliente"));
            incidence.setClientName(rs.getString("nombreCliente"));
            incidence.setId_worker(rs.getInt("I.idTrabajador"));
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
            incidence.setMedia(rs.getBytes("fotografia") == null ? null : rs.getBytes("fotografia"));

            return incidence;
        };
        List<Incidence> incidences = jdbcTemplate.query(SELECT_ALL_INCIDENCES, mapper);

        if (incidences.isEmpty()) {
            throw new EmptyIncidenceList("Empty incidence list");
        } else {
            return incidences;
        }
    }

    @Override
    public boolean updateWorker(int idIncidence, String idWorker) {
        Object[] args = new Object[]{idWorker, idIncidence};

        int rowsAffected = jdbcTemplate.update(UPDATE_WORKER, args);
        return rowsAffected > 0;
    }
}
