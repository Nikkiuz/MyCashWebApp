package it.daylight.MyCashWebApp.controller;

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

import it.daylight.MyCashWebApp.dto.GoalsRequestDTO;
import it.daylight.MyCashWebApp.dto.GoalsResponseDTO;
import it.daylight.MyCashWebApp.entity.Goals;
import it.daylight.MyCashWebApp.entity.GoalsCategories;
import it.daylight.MyCashWebApp.entity.User;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalsController {

    private final GoalsService goalsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GoalsResponseDTO> getAllGoals() {
        return ResponseEntity.ok(goalsService.getAllGoals());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GoalsResponseDTO> getGoalById(@PathVariable Long id) {
        return ResponseEntity.ok(goalsService.getGoalById(id));
    }

    @GetMapping("/filtered")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GoalsResponseDTO> getFilteredGoals(
            @RequestParam(required = false) Double amount,
            @RequestParam(required = false) User user,
            @RequestParam(required = false) Date expirationDate,
            @RequestParam(required = false) GoalsCategories goalsCategories) {
        return ResponseEntity.ok(goalsService.getFilteredGoals(amount, user, expirationDate, goalsCategories));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GoalsResponseDTO> createGoal(@RequestBody GoalsRequestDTO goalsRequestDTO) {
        return ResponseEntity.ok(goalsService.createGoal(goalsRequestDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GoalsResponseDTO> updateGoal(@PathVariable Long id,
            @RequestBody GoalsRequestDTO goalsRequestDTO) {
        return ResponseEntity.ok(goalsService.updateGoal(id, goalsRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGoal(@PathVariable Long id) {
        goalsService.deleteGoal(id);
        return ResponseEntity.ok("Obiettivo eliminato");
    }

}
