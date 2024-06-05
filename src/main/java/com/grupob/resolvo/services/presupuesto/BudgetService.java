package com.grupob.resolvo.services.presupuesto;

import com.grupob.resolvo.model.exception.NoBudgetFoundException;
import com.grupob.resolvo.model.presupuesto.Budget;
import com.grupob.resolvo.repository.presupuesto.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BudgetService {

    private BudgetRepository budgetRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public boolean insertBudget(Budget budget) {
        return budgetRepository.insertBudget(budget);
    }


    public boolean updateBudget(int id, Budget budget) {
        return budgetRepository.updateBudget(id, budget);
    }

    public Budget getBudgetByIncidence(int id) throws NoBudgetFoundException {
        final Budget budget = budgetRepository.getBudgetByIncidence(id);

        if(budget == null){
            throw new NoBudgetFoundException("Budget not found");
        }else{
            return budget;
        }
    }
}
