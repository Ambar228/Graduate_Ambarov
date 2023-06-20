package edu.bru.commentmicroservice.repository;

import edu.bru.commentmicroservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByNumberPhone(String numberPhone);
}
