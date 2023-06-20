package edu.bru.eventmicroservice.repository;

import edu.bru.eventmicroservice.model.Sponsor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends CrudRepository<Sponsor, Long> {
}
