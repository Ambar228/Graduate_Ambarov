package edu.bru.member.microservice.controller;

import edu.bru.member.microservice.model.Result;
import edu.bru.member.microservice.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @GetMapping
    public ResponseEntity<List<Result>> get() {
        return ResponseEntity.ok(resultService.get());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<List<Result>> get(@PathVariable Long eventId) {
        return ResponseEntity.ok(resultService.getWinners(eventId));
    }

    @PostMapping
    public ResponseEntity<Void> add(Result sponsor) {
        resultService.add(sponsor);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(Long id, Result sponsor) {
        resultService.update(id, sponsor);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        resultService.delete(id);

        return ResponseEntity.ok().build();
    }
}
