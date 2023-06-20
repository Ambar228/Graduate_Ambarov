package edu.bru.eventmicroservice.service;


import edu.bru.eventmicroservice.dto.EventDto;
import edu.bru.eventmicroservice.model.Comment;
import edu.bru.eventmicroservice.model.Event;
import edu.bru.eventmicroservice.model.User;

import java.io.IOException;
import java.util.List;

public interface EventService {
    List<Event> get();
    Event getById(Long id);
    Event getByName(String name);
    List<Event> getByState(String state);
    void add(EventDto event, String username) throws IOException;
    void addSponsor(Long id, Long sponsorId);
    void addTournament(Long id, Long tournamentId);
    void addRacer(Long id, Long racerId);
    void update(Long id, EventDto event, String username) throws IOException;
    void delete(Long id, String username);
}
