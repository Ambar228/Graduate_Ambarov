package edu.bru.sponsormicroservce.repository;

import edu.bru.sponsormicroservce.model.Sponsor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends CrudRepository<Sponsor, Long> {
}
