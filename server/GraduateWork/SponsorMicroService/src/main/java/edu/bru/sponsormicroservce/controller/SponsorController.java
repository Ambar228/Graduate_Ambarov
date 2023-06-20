package edu.bru.sponsormicroservce.controller;

import edu.bru.sponsormicroservce.dto.SponsorDto;
import edu.bru.sponsormicroservce.model.Sponsor;
import edu.bru.sponsormicroservce.service.SponsorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/sponsor")
@RequiredArgsConstructor
public class SponsorController {
    private final SponsorService sponsorService;

    @GetMapping
    public ResponseEntity<List<Sponsor>> get() {
        return ResponseEntity.ok(sponsorService.get());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> add(SponsorDto sponsor) throws IOException {
        sponsorService.add(sponsor);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, SponsorDto sponsor) throws IOException {
        sponsorService.update(id, sponsor);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        sponsorService.delete(id);

        return ResponseEntity.ok().build();
    }
}
