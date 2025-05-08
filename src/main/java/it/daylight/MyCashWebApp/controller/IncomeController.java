package it.daylight.MyCashWebApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import it.daylight.MyCashWebApp.dto.IncomeResponseDTO;
import it.daylight.MyCashWebApp.entity.Income;
import it.daylight.MyCashWebApp.entity.IncomeCategories;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IncomeResponseDTO> getAllIncomes() {
        return ResponseEntity.ok(incomeService.getAllIncomes());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IncomeResponseDTO> getIncomeById(@PathVariable Long id) {
        return ResponseEntity.ok(incomeService.getIncomeById(id));
    }

    @GetMapping("/filtered")
    public List<IncomeResponseDTO> getFilteredIncomes(
            @RequestParam(required = false) IncomeCategories incomeCategories,
            @RequestParam(required = false) LocalDateTime date,
            @RequestParam(required = false) Double amount) {
        return incomeService.getFilteredIncomes(incomeCategories, date, amount);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IncomeResponseDTO> createIncome(@RequestBody IncomeRequestDTO incomeRequestDTO) {
        return ResponseEntity.ok(incomeService.createIncome(incomeRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeResponseDTO> updateIncome(@PathVariable Long id,
            @RequestBody IncomeRequestDTO incomeRequestDTO) {
        return ResponseEntity.ok(incomeService.updateIncome(id, incomeRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.ok("Entrata eliminata");
    }

}
