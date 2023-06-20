package edu.bru.member.microservice.repository;

import edu.bru.member.microservice.model.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {
    List<Result> findByEventId(Long id);
}
