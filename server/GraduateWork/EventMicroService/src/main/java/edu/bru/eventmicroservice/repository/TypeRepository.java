package edu.bru.eventmicroservice.repository;

import edu.bru.eventmicroservice.model.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends CrudRepository<Type, Long> {
    Type findByName(String name);
}
