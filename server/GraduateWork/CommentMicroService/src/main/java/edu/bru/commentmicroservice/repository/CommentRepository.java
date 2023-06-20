package edu.bru.commentmicroservice.repository;

import edu.bru.commentmicroservice.model.Comment;
import edu.bru.commentmicroservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> getByUser(User user);
}
