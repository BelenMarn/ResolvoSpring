package com.grupob.resolvo.repository.presupuesto;

import com.grupob.resolvo.controller.presupuesto.BudgetRestController;
import com.grupob.resolvo.model.exception.NoBudgetFoundException;
import com.grupob.resolvo.model.exception.NoIncidenceFoundException;
import com.grupob.resolvo.model.incidencia.Incidence;
import com.grupob.resolvo.model.presupuesto.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    private final String SELECT_BUDGET_BY_INCIDENCE = "SELECT idPresupuesto, idIncidencia, IdCliente, descripcion, coste, aceptado " +
                                                 "FROM presupuesto WHERE idIncidencia = ?";

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

    @Override
    public Budget getBudgetByIncidence(int id) throws NoBudgetFoundException {

        RowMapper<Budget> mapper = (rs, rowNum) -> {
            Budget budget = new Budget();

            budget.setId_budget(rs.getInt("idPresupuesto"));
            budget.setId_incidence(rs.getInt("idIncidencia"));
            budget.setId_client(rs.getInt("idCliente"));
            budget.setDescription(rs.getString("descripcion"));
            budget.setCost(rs.getBigDecimal("coste"));
            budget.setAccepted(rs.getBoolean("aceptado"));

            return budget;
        };

        Budget budget = jdbcTemplate.queryForObject(SELECT_BUDGET_BY_INCIDENCE, mapper, id);

        if (budget != null) {
            return budget;
        } else {
            throw new NoBudgetFoundException("Budget not found");
        }

    }


}
