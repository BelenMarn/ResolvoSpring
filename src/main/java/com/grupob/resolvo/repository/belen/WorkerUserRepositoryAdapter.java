package com.grupob.resolvo.repository.belen;

import com.grupob.resolvo.model.belen.WorkerUser;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorkerUserRepositoryAdapter implements WorkerUserRepository {

    private final String SELECT_WORKER = "SELECT idTrabajador FROM usuariointerno " +
                                            "WHERE email = ? AND material = ?";
    private final String SELECT_FIRST_TIME = "SELECT primeraVez FROM usuariointerno WHERE idtrabajador = ?";
    private final String UPDATE_PASSWORD = "UPDATE usuariointerno SET material = ? WHERE idtrabajador = ?";
    private final String UPDATE_FIRST_TIME = "UPDATE usuariointerno SET primeraVez = 0 WHERE idtrabajador = ?";
    private final String SELECT_ID_WORKER = "SELECT idTrabajador FROM usuariointerno " +
                                            "WHERE email = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public WorkerUser findWorkerWithCredentials(String email, String password) throws NoWorkerUserFoundException {
        try{
            int id = jdbcTemplate.queryForObject(SELECT_WORKER, Integer.class, email, password);
            WorkerUser user = new WorkerUser();
            user.setId_worker(id);
            user.setEmail(email);
            user.setMaterial(password);

            if (user == null) {
                throw new NoWorkerUserFoundException("User not found");
            }

            return user;

        }catch (EmptyResultDataAccessException e){
            throw new NoWorkerUserFoundException("User not found");
        }
    }

    @Override
    public boolean findIfFirstTime(int id) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(SELECT_FIRST_TIME, new Object[]{id}, Boolean.class));
    }

    @Override
    public boolean updatePassword(int id, String password) {
        int rowsUpdated = jdbcTemplate.update(UPDATE_PASSWORD, password, id);
        return rowsUpdated > 0; // Return true if at least one row is updated
}

    @Override
    public void changeFirstTime(int id) {
        jdbcTemplate.update(UPDATE_FIRST_TIME, id);
    }

    @Override
    public int findIdByEmail(String email) throws NoWorkerUserFoundException {
        Integer id = jdbcTemplate.queryForObject(SELECT_ID_WORKER, new Object[]{email}, Integer.class);

        if(id != null){
            return id;
        }else{
            throw new NoWorkerUserFoundException("User not found");
        }
    }

}
