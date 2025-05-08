package it.daylight.MyCashWebApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.daylight.MyCashWebApp.dto.BudgetResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BudgetResponseDTO> getBudget() {
        return ResponseEntity.ok(budgetService.getBudget());
    }

}
