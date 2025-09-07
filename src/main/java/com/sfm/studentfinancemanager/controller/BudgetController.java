package com.sfm.studentfinancemanager.controller;

import com.sfm.studentfinancemanager.model.Budget;
import com.sfm.studentfinancemanager.repository.BudgetRepository;
import com.sfm.studentfinancemanager.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetRepository budgetRepository;
    private final TransactionRepository transactionRepository;

    public BudgetController(BudgetRepository budgetRepository, TransactionRepository transactionRepository) {
        this.budgetRepository = budgetRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/user/{userId}")
    public List<Budget> getBudgetsByUser(@PathVariable Long userId) {
        return budgetRepository.findByUserId(userId);
    }

    @PostMapping
    public Budget create(@RequestBody Budget budget) {
        return budgetRepository.save(budget);
    }

    @PutMapping("/{id}")
    public Budget update(@PathVariable Long id, @RequestBody Budget budget) {
        budget.setId(id);
        return budgetRepository.save(budget);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        budgetRepository.deleteById(id);
    }

    @GetMapping("/{id}/usage")
    public Double getUsage(@PathVariable Long id) {
        Budget budget = budgetRepository.findById(id).orElseThrow();
        Double spent = transactionRepository.findAll().stream()
                .filter(t -> t.getUserId().equals(budget.getUserId())) // 🔑 filtre aussi par utilisateur
                .filter(t -> t.getCategory().equalsIgnoreCase(budget.getCategory()))
                .mapToDouble(t -> t.getAmount())
                .sum();
        return (spent / budget.getLimitAmount()) * 100;
    }
}
