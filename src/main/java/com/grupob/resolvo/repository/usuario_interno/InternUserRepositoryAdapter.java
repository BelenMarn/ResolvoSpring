package com.grupob.resolvo.repository.usuario_interno;

import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.usuario_interno.InternUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


//MARCOS
@Repository
public class InternUserRepositoryAdapter implements InternUserRepository {
    private static Logger logger = LoggerFactory.getLogger(InternUserRepositoryAdapter.class);

    private final String SELECT_FIRST_TIME = "SELECT primeraVez FROM usuariointerno WHERE email = ?";
    private final String CHANGE_FRIST_TIME = "UPDATE usuariointerno SET primeraVez=false WHERE email= ?";
    private final String SELECT_INTERN_USER = "SELECT idTrabajador, email, material, primeraVez FROM usuariointerno WHERE  email = ?  AND material = ? ";
    private final String UPDATE_MATERIAL = "UPDATE usuarioInterno SET material= ? WHERE email= ? ";
    private final String SELECT_USER_BY_EMAIL = "SELECT idTrabajador, email, material, primeraVez FROM usuariointerno WHERE  email = ?";

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
    public void changeFirstTime(String email) {
        jdbcTemplate.update(CHANGE_FRIST_TIME, email);
    }

    @Override
    public InternUser findInternUser(String email, String password) throws NoWorkerUserFoundException {
        final InternUser user = jdbcTemplate.queryForObject(SELECT_INTERN_USER, new Object[]{email, password}, (rs, rowNum) -> new InternUser(
                rs.getInt("idTrabajador"),
                rs.getString("email"),
                rs.getString("material"),
                rs.getBoolean("primeraVez")
        ));

        if (user == null) {
            throw new NoWorkerUserFoundException("User not found");
        }
        return user;
    }

    @Override
    public void updateMaterial(String email, String password) {
        jdbcTemplate.update(UPDATE_MATERIAL, password, email);
    }

    @Override
    public InternUser findInternUserByEmail(String email) throws NoWorkerUserFoundException {
        String[] args = { email };
        String formattedQuery = SELECT_USER_BY_EMAIL + " " + email;
        logger.info("Executing query: {}", formattedQuery);

        final InternUser user = jdbcTemplate.queryForObject(
                SELECT_USER_BY_EMAIL,
                args,
                (rs, rowNum) -> new InternUser(
                        rs.getInt("idTrabajador"),
                        rs.getString("email"),
                        rs.getString("material"),
                        rs.getBoolean("primeraVez")
                )
        );

        if (user == null) {
            throw new NoWorkerUserFoundException("User not found");
        }

        return user;
    }
}
