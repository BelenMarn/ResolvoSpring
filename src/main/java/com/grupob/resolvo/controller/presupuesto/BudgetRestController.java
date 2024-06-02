package com.grupob.resolvo.controller.presupuesto;

import com.grupob.resolvo.model.presupuesto.Budget;
import com.grupob.resolvo.services.presupuesto.BudgetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
public class BudgetRestController {

    private BudgetService budgetService;
    public BudgetRestController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping("/newBudget")
    public boolean insertBudget(@RequestBody Budget budget) {
        return budgetService.insertBudget(budget);
    }

    //java.math.BigDecimal se representa con punto --> 20.0
    @PutMapping("/update/{id}")
    public boolean updateBudget(@PathVariable("id")int id, @RequestBody Budget budget) {
        return budgetService.updateBudget(id, budget);
    }
}
