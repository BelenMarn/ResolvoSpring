package com.grupob.resolvo.repository.usuario_externo;

import com.grupob.resolvo.model.exception.NoClientUserFoundException;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.usuario_externo.ExternUser;
import com.grupob.resolvo.model.usuario_interno.InternUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExternUserRepositoryAdapter implements ExternUserRepository {

    private final String SELECT_USER_BY_EMAIL = "SELECT idCliente, email, material FROM usuarioexterno WHERE  email = ?";
    private final String INSERT_USER = "INSERT INTO usuarioexterno (idCliente, email, material ) VALUES (?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ExternUser findExternUserByEmail(String email) throws NoClientUserFoundException {
        String[] args = { email };

        final ExternUser user = jdbcTemplate.queryForObject(
                SELECT_USER_BY_EMAIL,
                args,
                (rs, rowNum) -> new ExternUser(
                        rs.getInt("idCliente"),
                        rs.getString("email"),
                        rs.getString("material")
                )
        );

        if (user == null) {
            throw new NoClientUserFoundException("Client not found");
        }

        return user;
    }

    @Override
    public void newUser(ExternUser externUser) {
        try {
            jdbcTemplate.update(INSERT_USER,
                    externUser.getId_client(),
                    externUser.getEmail(),
                    externUser.getMaterial()
            );

        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }
}
