package it.daylight.MyCashWebApp.controller;

import it.daylight.MyCashWebApp.service.IncomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import it.daylight.MyCashWebApp.dto.IncomeRequestDTO;
import it.daylight.MyCashWebApp.dto.IncomeResponseDTO;
import it.daylight.MyCashWebApp.entity.IncomeCategories;
import it.daylight.MyCashWebApp.entity.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IncomeResponseDTO> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IncomeResponseDTO> getIncomeById(@PathVariable Long id) {
        return ResponseEntity.ok(incomeService.getIncomeById(id));
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<IncomeResponseDTO> getIncomesByUser(@PathVariable Long id) {
        return incomeService.getIncomesByUserId(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<IncomeResponseDTO> getIncomesByAmount(@PathVariable Double amount) {
        return incomeService.getIncomesByAmount(amount);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<IncomeResponseDTO> getIncomesByDate(@PathVariable Date date) {
        return incomeService.getIncomesByDate(date);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<IncomeResponseDTO> getIncomesByCategory(@PathVariable IncomeCategories category) {
        return incomeService.getIncomesByCategory(category);
    }

    @GetMapping("/filtered")
    public List<IncomeResponseDTO> getFilteredIncomes(
            @RequestParam(required = false) IncomeCategories incomeCategories,
            @RequestParam(required = false) Date date,
            @RequestParam(required = false) Double amount,
            @RequestParam(required = false) User user) {
        return incomeService.getFilteredIncomes(incomeCategories, date, amount, user);
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
