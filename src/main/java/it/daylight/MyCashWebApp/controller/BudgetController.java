package it.daylight.MyCashWebApp.controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping("/monthly")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> getMonthlyBudget(@RequestParam int year, @RequestParam int month,
                                                     @RequestParam(required = false) Long userId) {
        double budget = budgetService.calculateMonthlyBudget(year, month, userId);
        return ResponseEntity.ok(budget);
    }

    @GetMapping("/annual")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> getAnnualBudget(@RequestParam int year,
                                                     @RequestParam(required = false) Long userId) {
        double budget = budgetService.calculateAnnualBudget(year, userId);
        return ResponseEntity.ok(budget);
    }
}