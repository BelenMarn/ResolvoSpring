package com.grupob.resolvo.repository.presupuesto;

import com.grupob.resolvo.model.presupuesto.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class BudgetRepositoryAdapter implements BudgetRepository {

    private final String INSERT_BUDGET = "INSERT INTO presupuesto(" +
                                        "idIncidencia, idCliente, descripcion, coste) " +
                                        "VALUES(?, ?, ?, ?)";

    private final String UPDATE_COST_DESCRIPTION = "UPDATE presupuesto SET " +
                                            "descripcion = ?, coste = ? " +
                                            "WHERE idIncidencia = ?";

    private JdbcTemplate jdbcTemplate;
    public BudgetRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insertBudget(Budget budget) {
        Object[] args = new Object[]{budget.getId_incidence(), budget.getId_client(),
                budget.getDescription(), budget.getCost()};

        int rowsAffected = jdbcTemplate.update(INSERT_BUDGET, args);
        return rowsAffected > 0;
    }

    @Override
    public boolean updateBudget(int id, Budget budget) {
        Object[] args = new Object[]{budget.getDescription(), budget.getCost(), id};

        int rowsAffected = jdbcTemplate.update(UPDATE_COST_DESCRIPTION, args);
        return rowsAffected > 0;
    }


}
