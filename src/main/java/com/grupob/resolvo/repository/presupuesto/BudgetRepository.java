package com.grupob.resolvo.repository.presupuesto;

import com.grupob.resolvo.model.exception.NoBudgetFoundException;
import com.grupob.resolvo.model.presupuesto.Budget;

import java.math.BigDecimal;

public interface BudgetRepository {
    boolean insertBudget(Budget budget);
    boolean updateBudget(int id, Budget budget);
    Budget getBudgetByIncidence(int id) throws NoBudgetFoundException;
}
