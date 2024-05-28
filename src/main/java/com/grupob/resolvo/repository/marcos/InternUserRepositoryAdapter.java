package com.grupob.resolvo.repository.marcos;

import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.marcos.InternUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//MARCOS
@Repository
public class InternUserRepositoryAdapter implements InternUserRepository {

    private final String SELECT_FIRST_TIME = "SELECT primeraVez FROM usuariointerno WHERE email = ?";
    private final String CHANGE_FRIST_TIME = "UPDATE usuariointerno SET primeraVez=false WHERE email= ?";
    private final String SELECT_INTERN_USER = "SELECT * FROM usuariointerno WHERE  email = ?  AND material = ? ";
    private final String UPDATE_MATERIAL = "UPDATE usuarioInterno SET material= ? WHERE email= ? ";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean findIfFirstTime(String email) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(SELECT_FIRST_TIME, new Object[]{email}, Boolean.class));
    }

    @Override
    public void changeFirstTime(String email, String password) {
        jdbcTemplate.update(CHANGE_FRIST_TIME, new Object[]{email, password});
    }

    @Override
    public InternUser findInternUser(String email, String password) throws NoWorkerUserFoundException {
        final InternUser user = jdbcTemplate.queryForObject(SELECT_INTERN_USER, new Object[]{email, password}, InternUser.class);

        if (user == null) {
            throw new NoWorkerUserFoundException("User not found");
        }else{
            return user;
        }
    }

    @Override
    public void updateMaterial(String email, String password) {
        jdbcTemplate.update(UPDATE_MATERIAL, new Object[]{email, password});
    }
}
