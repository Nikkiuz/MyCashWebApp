package it.daylight.MyCashWebApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import org.springframework.http.HttpStatus;

import it.daylight.MyCashWebApp.dto.OutcomeResponseDTO;
import it.daylight.MyCashWebApp.entity.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/outcome")
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
            @RequestParam(required = false) LocalDateTime date,
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
