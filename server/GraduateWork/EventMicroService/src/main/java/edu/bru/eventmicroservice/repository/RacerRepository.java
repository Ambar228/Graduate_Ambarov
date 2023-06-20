package edu.bru.eventmicroservice.repository;

import edu.bru.eventmicroservice.model.Racer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacerRepository extends CrudRepository<Racer, Long> {
}
