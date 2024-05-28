package com.grupob.resolvo.repository.marcos;

import com.grupob.resolvo.model.enums.Position;
import com.grupob.resolvo.model.enums.Specialization;
import com.grupob.resolvo.model.exception.EmptyWorkerList;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.marcos.WorkerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class WorkerDataRepositoryAdapter implements WorkerDataRepository {

    private final String SELECT_WORKER_BY_EMAIL = "SELECT * FROM trabajador WHERE email = ?";
    private final String SELECT_ALL_WORKERS = "SELECT * FROM trabajador";

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
        worker.setEmail(String.valueOf((int) workerData.get("idTrabajador")));
        worker.setName((String) workerData.get("nombre"));
        worker.setSurname((String) workerData.get("apellidos"));
        worker.setStreet((String) workerData.get("calle"));
        worker.setPostal_code((String) workerData.get("codPostal"));
        worker.setCity((String) workerData.get("ciudad"));
        worker.setProvince((String) workerData.get("provincia"));
        worker.setPhone((String) workerData.get("telefono"));
        worker.setDni((String) workerData.get("dni"));
        worker.setEmail((String) workerData.get("email"));
        worker.setBirthday_date((Date) workerData.get("fechaNacimiento"));

        String positionString = (String) workerData.get("position");
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
            worker.setName(rs.getString("nombre"));
            worker.setSurname(rs.getString("apellido"));
            worker.setStreet(rs.getString("street"));
            worker.setPostal_code(rs.getString("postal_code"));
            worker.setCity(rs.getString("ciudad"));
            worker.setProvince(rs.getString("provincia"));
            worker.setPhone(rs.getString("telefono"));
            worker.setDni(rs.getString("dni"));
            worker.setEmail(rs.getString("email"));
            worker.setBirthday_date(rs.getTimestamp("birthday_date"));
            worker.setPosition(Position.fromString(rs.getString("posicion")));
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
}
