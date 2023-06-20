package edu.bru.usermicroservice.repository;

import edu.bru.usermicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNumberPhone(String numberPhone);
}
