package edu.bru.usermicroservice.repository;

import edu.bru.usermicroservice.model.Racer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacerRepository extends CrudRepository<Racer, Long> {
}
