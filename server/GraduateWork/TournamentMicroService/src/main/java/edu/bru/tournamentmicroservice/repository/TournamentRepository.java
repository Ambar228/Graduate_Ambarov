package edu.bru.tournamentmicroservice.repository;

import edu.bru.tournamentmicroservice.model.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long> {
}
