package edu.bru.commentmicroservice.service;

import edu.bru.commentmicroservice.dto.CommentDto;
import edu.bru.commentmicroservice.model.Comment;
import edu.bru.commentmicroservice.model.User;

import java.io.IOException;
import java.util.List;

public interface CommentService {
    List<Comment> get();
    void add(Long id, CommentDto comment, String username) throws IOException;
    List<Comment> getByUser(String username);
    void update(Long eventId, CommentDto comment, Long id, String username) throws IOException;
    void delete(Long eventId, Long id, String username);

}
