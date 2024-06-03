package com.grupob.resolvo.repository.trabajador;

import com.grupob.resolvo.model.enums.Position;
import com.grupob.resolvo.model.enums.Specialization;
import com.grupob.resolvo.model.exception.EmptyWorkerList;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.trabajador.WorkerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class WorkerDataRepositoryAdapter implements WorkerDataRepository {

    private final String SELECT_WORKER_BY_EMAIL = "SELECT * FROM trabajador WHERE email = ?";
    private final String SELECT_ALL_WORKERS = "SELECT * FROM trabajador";
    private final String SELECT_WORKERS_BY_POSTAL_CODE = "SELECT * FROM trabajador WHERE codPostal = ?";
    private final String SELECT_WORKERS_BY_POSITION = "SELECT * FROM trabajador WHERE cargo = ?";
    private final String SELECT_WORKERS_BY_SPECIALIZATION = "SELECT * FROM trabajador WHERE especializacion = ?";

    private final String INSERT_WORKER = "INSERT INTO trabajador(nombre, apellidos, calle, codPostal, ciudad, " +
                                        "provincia, telefono, dni, fechaNacimiento, cargo, especializacion) " +
                                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String DELETE_WORKER = "DELETE FROM trabajador WHERE idTrabajador = ?";

    private JdbcTemplate jdbcTemplate;
    public WorkerDataRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public WorkerData findWorkerDataByEmail(String email) throws NoWorkerUserFoundException {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_WORKER_BY_EMAIL, email);

        if (rows.isEmpty()) {
            throw new NoWorkerUserFoundException("Worker not found");
        }

        Map<String, Object> workerData = rows.get(0);

        WorkerData worker = new WorkerData();
        worker.setIdWorker((int) ((Long) workerData.get("idTrabajador")).longValue());
        worker.setName((String) workerData.get("nombre"));
        worker.setSurname((String) workerData.get("apellidos"));
        worker.setStreet((String) workerData.get("calle"));
        worker.setPostal_code((String) workerData.get("codPostal"));
        worker.setCity((String) workerData.get("ciudad"));
        worker.setProvince((String) workerData.get("provincia"));
        worker.setPhone((String) workerData.get("telefono"));
        worker.setDni((String) workerData.get("dni"));
        worker.setEmail((String) workerData.get("email"));
        worker.setBirthday_date((java.sql.Date) workerData.get("fechaNacimiento"));

        String positionString = (String) workerData.get("cargo");
        Position position = Position.fromString(positionString);
        worker.setPosition(position);

        String specializationString = (String) workerData.get("especializacion");
        Specialization specialization = Specialization.fromString(specializationString);
        worker.setSpecialization(specialization);

        return worker;
    }

    @Override
    public List<WorkerData> findWorkerList() throws EmptyWorkerList {
        RowMapper<WorkerData> mapper = (rs, rowNum) -> {

            WorkerData worker = new WorkerData();
            worker.setIdWorker((int) ((Long) rs.getObject("idTrabajador")).longValue());
            worker.setName(rs.getString("nombre"));
            worker.setSurname(rs.getString("apellidos"));
            worker.setStreet(rs.getString("calle"));
            worker.setPostal_code(rs.getString("codPostal"));
            worker.setCity(rs.getString("ciudad"));
            worker.setProvince(rs.getString("provincia"));
            worker.setPhone(rs.getString("telefono"));
            worker.setDni(rs.getString("dni"));
            worker.setEmail(rs.getString("email"));
            worker.setBirthday_date(rs.getDate("fechaNacimiento"));

            String positionString = rs.getString("cargo");
            worker.setPosition(Position.fromString(positionString.toLowerCase()));
            worker.setSpecialization(Specialization.fromString(rs.getString("especializacion")));

            return worker;
        };
        List<WorkerData> workers = jdbcTemplate.query(SELECT_ALL_WORKERS, mapper);

        if (workers.isEmpty()) {
            throw new EmptyWorkerList("No workers found");
        } else {
            return workers;
        }
    }

    @Override
    public List<WorkerData> findWorkersDataByPostalCode(String code) throws EmptyWorkerList {
        RowMapper<WorkerData> mapper = (rs, rowNum) -> {

            WorkerData worker = new WorkerData();
            worker.setIdWorker((int) ((Long) rs.getObject("idTrabajador")).longValue());
            worker.setName(rs.getString("nombre"));
            worker.setSurname(rs.getString("apellidos"));
            worker.setStreet(rs.getString("calle"));
            worker.setPostal_code(rs.getString("codPostal"));
            worker.setCity(rs.getString("ciudad"));
            worker.setProvince(rs.getString("provincia"));
            worker.setPhone(rs.getString("telefono"));
            worker.setDni(rs.getString("dni"));
            worker.setEmail(rs.getString("email"));
            worker.setBirthday_date(rs.getDate("fechaNacimiento"));

            String positionString = rs.getString("cargo");
            worker.setPosition(Position.fromString(positionString.toLowerCase()));
            worker.setSpecialization(Specialization.fromString(rs.getString("especializacion")));

            return worker;
        };

        List<WorkerData> workers = jdbcTemplate.query(SELECT_WORKERS_BY_POSTAL_CODE, new Object[]{code}, mapper);

        if (workers.isEmpty()) {
            throw new EmptyWorkerList("No workers found");
        } else {
            return workers;
        }
    }

    @Override
    public List<WorkerData> findWorkersDataByPosition(String position) throws EmptyWorkerList {
        RowMapper<WorkerData> mapper = (rs, rowNum) -> {

            WorkerData worker = new WorkerData();
            worker.setIdWorker((int) ((Long) rs.getObject("idTrabajador")).longValue());
            worker.setName(rs.getString("nombre"));
            worker.setSurname(rs.getString("apellidos"));
            worker.setStreet(rs.getString("calle"));
            worker.setPostal_code(rs.getString("codPostal"));
            worker.setCity(rs.getString("ciudad"));
            worker.setProvince(rs.getString("provincia"));
            worker.setPhone(rs.getString("telefono"));
            worker.setDni(rs.getString("dni"));
            worker.setEmail(rs.getString("email"));
            worker.setBirthday_date(rs.getDate("fechaNacimiento"));

            String positionString = rs.getString("cargo");
            worker.setPosition(Position.fromString(positionString.toLowerCase()));
            worker.setSpecialization(Specialization.fromString(rs.getString("especializacion")));

            return worker;
        };

        List<WorkerData> workers = jdbcTemplate.query(SELECT_WORKERS_BY_POSITION, new Object[]{position}, mapper);

        if (workers.isEmpty()) {
            throw new EmptyWorkerList("No workers found");
        } else {
            return workers;
        }
    }

    @Override
    public List<WorkerData> findWorkersDataBySpecialization(String spe) throws EmptyWorkerList {
        RowMapper<WorkerData> mapper = (rs, rowNum) -> {

            WorkerData worker = new WorkerData();
            worker.setIdWorker((int) ((Long) rs.getObject("idTrabajador")).longValue());
            worker.setName(rs.getString("nombre"));
            worker.setSurname(rs.getString("apellidos"));
            worker.setStreet(rs.getString("calle"));
            worker.setPostal_code(rs.getString("codPostal"));
            worker.setCity(rs.getString("ciudad"));
            worker.setProvince(rs.getString("provincia"));
            worker.setPhone(rs.getString("telefono"));
            worker.setDni(rs.getString("dni"));
            worker.setEmail(rs.getString("email"));
            worker.setBirthday_date(rs.getDate("fechaNacimiento"));

            String positionString = rs.getString("cargo");
            worker.setPosition(Position.fromString(positionString.toLowerCase()));
            worker.setSpecialization(Specialization.fromString(rs.getString("especializacion")));

            return worker;
        };

        List<WorkerData> workers = jdbcTemplate.query(SELECT_WORKERS_BY_SPECIALIZATION, new Object[]{spe}, mapper);

        if (workers.isEmpty()) {
            throw new EmptyWorkerList("No workers found");
        } else {
            return workers;
        }
    }

    @Override
    public void addWorkerData(WorkerData workerData) {
        System.out.println(workerData.toString());

        try {
            jdbcTemplate.update(INSERT_WORKER,
                    workerData.getName(),
                    workerData.getSurname(),
                    workerData.getStreet(),
                    workerData.getPostal_code(),
                    workerData.getCity(),
                    workerData.getProvince(),
                    workerData.getPhone(),
                    workerData.getDni(),
                    workerData.getBirthday_date(),
                    workerData.getPosition().name().toLowerCase(),
                    workerData.getSpecialization().name().toLowerCase()
            );

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteWorkerData(String id) {
        int rowsAffected = jdbcTemplate.update(DELETE_WORKER, id);
        return rowsAffected > 0;
    }
}
