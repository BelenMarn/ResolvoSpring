package com.grupob.resolvo.repository;

import com.grupob.resolvo.model.Technician;
import com.grupob.resolvo.model.WorkerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TechnicianRepositoryAdapter implements TechnicianRepository{

    private final String SELECT_COUNT_USERS = "SELECT COUNT(*) FROM usuariointerno WHERE email = ? AND material = ?";
    private final String SELECT_FIRST_TIME = "SELECT primeraVez FROM usuariointerno WHERE email = ? AND material = ?";
    private final String SELECT_ID_WORKER = "SELECT idTrabajador FROM usuariointerno WHERE email = ? AND material = ?";
    private final String SELECT_TECHNICIAN = "SELECT `nombre`, `apellidos`, `calle`, `codPostal`, `ciudad`, `provincia`, " +
            "`telefono`, `dni`, `email`, `fechaNacimiento`, `cargo`, `especializacion` FROM `trabajador` WHERE `idTrabajador` = ?";
    private final String UPDATE_PASSWORD = "UPDATE usuariointerno SET material = ? WHERE email = ?";
    private final String UPDATE_FIRST_TIME = "UPDATE usuariointerno SET primeraVez = 0 WHERE email = ?";

    private JdbcTemplate jdbcTemplate;
    public TechnicianRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean findUsername(String username, String password) {
        int count = jdbcTemplate.queryForObject(SELECT_COUNT_USERS, new Object[]{username, password}, Integer.class);
        return count > 0;
    }

    @Override
    public boolean findIfFirstTime(String username, String password) {
        Integer isFirstTime = jdbcTemplate.queryForObject(SELECT_FIRST_TIME, new Object[]{username, password}, Integer.class);
        return isFirstTime != null && isFirstTime == 1;
    }

    @Override
    public int findIdWorker(String username, String password) {
        return jdbcTemplate.queryForObject(SELECT_ID_WORKER, new Object[]{username, password}, Integer.class);
    }

    @Override
    public Technician findTechnician(WorkerUser user) {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_TECHNICIAN, user.getId_worker());

        if (rows.isEmpty()) {
            return null;
        }

        Map<String, Object> technicianData = rows.get(0);

        Technician technician = new Technician();
        technician.setUser(user);
        technician.setName((String) technicianData.get("nombre"));
        technician.setSurname((String) technicianData.get("apellidos"));
        technician.setStreet((String) technicianData.get("calle"));
        technician.setPostal_code((String) technicianData.get("codPostal"));
        technician.setCity((String) technicianData.get("ciudad"));
        technician.setProvince((String) technicianData.get("provincia"));
        technician.setPhone((String) technicianData.get("telefono"));
        technician.setDni((String) technicianData.get("dni"));
        technician.setEmail((String) technicianData.get("email"));
        technician.setBirthday_date((String) technicianData.get("fechaNacimiento"));
        technician.setSpecialization((String) technicianData.get("especializacion"));

        return technician;
    }

    @Override
    public String updatePassword(String username, String password) {
        int rowsUpdated = jdbcTemplate.update(UPDATE_PASSWORD, password, username);
        return rowsUpdated > 0 ? "Password updated successfully" : "Password update failed";
    }

    @Override
    public void changeFirstTime(String username) {
        jdbcTemplate.update(UPDATE_FIRST_TIME, username);
    }
}