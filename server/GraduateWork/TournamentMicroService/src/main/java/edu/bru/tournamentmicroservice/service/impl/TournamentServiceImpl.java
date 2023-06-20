package edu.bru.tournamentmicroservice.service.impl;

import edu.bru.tournamentmicroservice.model.Tournament;
import edu.bru.tournamentmicroservice.repository.TournamentRepository;
import edu.bru.tournamentmicroservice.service.TournamentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {
    private final TournamentRepository tournamentRepository;

    @Override
    @Transactional
    public List<Tournament> get() {
        return (List<Tournament>) tournamentRepository.findAll();
    }

    @Override
    @Transactional
    public Tournament getById(Long id) {
        return tournamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Tournament is not exist"));
    }

    @Override
    @Transactional
    public void add(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    @Override
    @Transactional
    public void update(Long id, Tournament tournament) {
        Tournament tournamentForUpdate = tournamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Tournament is not exist"));
        tournamentForUpdate.setName(tournamentForUpdate.getName());
        tournamentRepository.save(tournamentForUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tournamentRepository.deleteById(id);
    }
}
