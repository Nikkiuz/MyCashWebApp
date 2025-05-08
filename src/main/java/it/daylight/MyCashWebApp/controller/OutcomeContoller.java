package it.daylight.MyCashWebApp.controller;

import it.daylight.MyCashWebApp.dto.OutcomeRequestDTO;
import it.daylight.MyCashWebApp.entity.OutcomeCategories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import org.springframework.http.HttpStatus;

import it.daylight.MyCashWebApp.dto.OutcomeResponseDTO;
import it.daylight.MyCashWebApp.entity.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/outcomes")
@RequiredArgsConstructor
public class OutcomeContoller {

    private final OutcomeService outcomeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OutcomeResponseDTO> getAllOutcomes() {
        return ResponseEntity.ok(outcomeService.getAllOutcomes());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OutcomeResponseDTO> getOutcomeById(@PathVariable Long id) {
        return ResponseEntity.ok(outcomeService.getOutcomeById(id));
    }

    @GetMapping("/filtered")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OutcomeResponseDTO> getFilteredOutcomes(
            @RequestParam(required = false) OutcomeCategories outcomeCategories,
            @RequestParam(required = false) Date date,
            @RequestParam(required = false) Double amount,
            @RequestParam(required = false) Date expirationDate,
            @RequestParam(required = false) User user) {
        return ResponseEntity
                .ok(outcomeService.getFilteredOutcomes(outcomeCategories, date, amount, expirationDate, user));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OutcomeResponseDTO> createOutcome(@RequestBody OutcomeRequestDTO outcomeRequestDTO) {
        return ResponseEntity.ok(outcomeService.createOutcome(outcomeRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutcomeResponseDTO> updateOutcome(@PathVariable Long id,
            @RequestBody OutcomeRequestDTO outcomeRequestDTO) {
        return ResponseEntity.ok(outcomeService.updateOutcome(id, outcomeRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOutcome(@PathVariable Long id) {
        outcomeService.deleteOutcome(id);
        return ResponseEntity.ok("Spesa eliminata");
    }
}
