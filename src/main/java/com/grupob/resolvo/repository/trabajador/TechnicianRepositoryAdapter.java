package com.grupob.resolvo.repository.trabajador;

import ch.qos.logback.classic.Logger;
import com.grupob.resolvo.model.enums.Specialization;
import com.grupob.resolvo.model.trabajador.Technician;
import com.grupob.resolvo.model.exception.NoTechnicianFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class TechnicianRepositoryAdapter implements TechnicianRepository{

    private final String SELECT_TECHNICIAN = "SELECT nombre, apellidos, calle, codPostal, ciudad, provincia, " +
            "telefono, dni, email, fechaNacimiento, cargo, especializacion FROM trabajador WHERE idTrabajador = ?";

    private final String SELECT_IF_TECHNICIAN = "SELECT cargo FROM trabajador WHERE idTrabajador = ?";


    private JdbcTemplate jdbcTemplate;
    public TechnicianRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Technician findTechnician(int id) throws NoTechnicianFoundException {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_TECHNICIAN, id);

        if (rows.isEmpty()) {
            throw new NoTechnicianFoundException("Technician not found");
        }

        Map<String, Object> technicianData = rows.get(0);

        Technician technician = new Technician();
        technician.setId_worker(id);
        technician.setName((String) technicianData.get("nombre"));
        technician.setSurname((String) technicianData.get("apellidos"));
        technician.setStreet((String) technicianData.get("calle"));
        technician.setPostal_code((String) technicianData.get("codPostal"));
        technician.setCity((String) technicianData.get("ciudad"));
        technician.setProvince((String) technicianData.get("provincia"));
        technician.setPhone((String) technicianData.get("telefono"));
        technician.setDni((String) technicianData.get("dni"));
        technician.setEmail((String) technicianData.get("email"));
        technician.setBirthday_date((Date) technicianData.get("fechaNacimiento"));

        String specializationString = (String) technicianData.get("especializacion");
        Specialization specialization = Specialization.fromString(specializationString);
        technician.setSpecialization(specialization);

        return technician;
    }

    @Override
    public boolean getIfTechnician(int id) throws NoTechnicianFoundException {
        try {
            String cargo = jdbcTemplate.queryForObject(SELECT_IF_TECHNICIAN, String.class, id);
            return cargo.equalsIgnoreCase("tecnico");

        }catch (Exception e) {
            throw new NoTechnicianFoundException("Technician not found");

        }

    }
}

