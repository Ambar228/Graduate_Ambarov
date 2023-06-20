package edu.bru.commentmicroservice.repository;

import edu.bru.commentmicroservice.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
