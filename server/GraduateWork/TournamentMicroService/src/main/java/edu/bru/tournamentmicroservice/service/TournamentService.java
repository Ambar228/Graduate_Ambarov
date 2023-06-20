package edu.bru.tournamentmicroservice.service;

import edu.bru.tournamentmicroservice.model.Tournament;

import java.util.List;

public interface TournamentService {
    List<Tournament> get();
    Tournament getById(Long id);
    void add(Tournament tournament);
    void update(Long id, Tournament tournament);
    void delete(Long id);
}
