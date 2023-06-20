package edu.bru.commentmicroservice.service.Impl;

import edu.bru.commentmicroservice.dto.CommentDto;
import edu.bru.commentmicroservice.model.Comment;
import edu.bru.commentmicroservice.model.Event;
import edu.bru.commentmicroservice.model.User;
import edu.bru.commentmicroservice.repository.CommentRepository;
import edu.bru.commentmicroservice.repository.EventRepository;
import edu.bru.commentmicroservice.repository.UserRepository;
import edu.bru.commentmicroservice.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.util.Arrays;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final WebClient webClient;

    @Override
    @Transactional
    public List<Comment> get() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    @Transactional
    public void add(Long id, CommentDto commentDto, String username) throws IOException {
        User user = userRepository.findByNumberPhone(username);

        Event event = eventRepository.findById(id).get();

        Comment comment = Comment.builder()
                .text(commentDto.getText())
                .date(LocalDateTime.now())
                .event(event)
                .changed(false)
                .user(user)
                .build();

        if (!(commentDto.getFile() == null)) {
            comment.setFile(commentDto.getFile().getBytes());
        }

        commentRepository.save(comment).getId();
    }

    @Override
    @Transactional
    public List<Comment> getByUser(String username) {
        User user = userRepository.findByNumberPhone(username);

        return commentRepository.getByUser(user);
    }

    @Override
    @Transactional
    public void update(Long eventId, CommentDto commentDto, Long id, String username) throws IOException {
        User user = userRepository.findByNumberPhone(username);

        Comment commentForUpdate = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment is not exist"));
        commentForUpdate.setChanged(true);
        commentForUpdate.setText(commentDto.getText());
        commentForUpdate.setDate(LocalDateTime.now());
        if (!(commentDto.getFile() == null)) {
            commentForUpdate.setFile(commentDto.getFile().getBytes());
        }

        commentRepository.save(commentForUpdate);
    }

    @Override
    @Transactional
    public void delete(Long eventId, Long id, String username) {
        User user = userRepository.findByNumberPhone(username);

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment is not exist"));
        if (comment.getUser().getNumberPhone().equals(user.getNumberPhone())) {
            commentRepository.deleteById(id);
        } else if (user.getRole().name().equals("MODERATOR")) {
            commentRepository.deleteById(id);
        }
    }
}
