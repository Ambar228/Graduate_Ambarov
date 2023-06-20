package edu.bru.eventmicroservice.controller;

import edu.bru.eventmicroservice.dto.EventDto;
import edu.bru.eventmicroservice.model.Comment;
import edu.bru.eventmicroservice.model.Event;
import edu.bru.eventmicroservice.model.User;
import edu.bru.eventmicroservice.security.JwtUtil;
import edu.bru.eventmicroservice.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<Event>> get() {
        return ResponseEntity.ok(eventService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Event> getByName(@RequestParam String name) {
        return ResponseEntity.ok(eventService.getByName(name));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> add(@ModelAttribute EventDto event, HttpServletRequest httpServletRequest) throws IOException {
        eventService.add(event, extractUserName(httpServletRequest));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/sponsor")
    public ResponseEntity<Void> addSponsor(@PathVariable Long id, @RequestParam Long sponsorId) {
        eventService.addSponsor(id, sponsorId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/racer")
    public ResponseEntity<Void> addRacer(@PathVariable Long id, @RequestParam Long racerId) {
        eventService.addRacer(id, racerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/tournament")
    public ResponseEntity<Void> addTournament(@PathVariable Long id, @RequestParam Long tournamentId) {
        eventService.addTournament(id, tournamentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @ModelAttribute EventDto event, HttpServletRequest httpServletRequest) throws IOException {
        eventService.update(id, event, extractUserName(httpServletRequest));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        eventService.delete(id, extractUserName(httpServletRequest));
        return ResponseEntity.ok().build();
    }

    private String extractUserName(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        return username;
    }
}
