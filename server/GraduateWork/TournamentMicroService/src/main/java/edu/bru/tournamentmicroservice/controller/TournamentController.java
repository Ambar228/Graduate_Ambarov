package edu.bru.tournamentmicroservice.controller;

import edu.bru.tournamentmicroservice.model.Tournament;
import edu.bru.tournamentmicroservice.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    @GetMapping
    public ResponseEntity<List<Tournament>> get() {
        return ResponseEntity.ok(tournamentService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> add(Tournament tournament) {
        tournamentService.add(tournament);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, Tournament tournament) {
        tournamentService.update(id, tournament);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tournamentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
