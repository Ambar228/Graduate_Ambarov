package edu.bru.commentmicroservice.controller;

import edu.bru.commentmicroservice.dto.CommentDto;
import edu.bru.commentmicroservice.model.Comment;
import edu.bru.commentmicroservice.model.User;
import edu.bru.commentmicroservice.security.JwtUtil;
import edu.bru.commentmicroservice.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final JwtUtil jwtUtil;

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> get() {
        return ResponseEntity.ok(commentService.get());
    }

    @GetMapping("/user")
    public ResponseEntity<List<Comment>> getByUser(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(commentService.getByUser(extractUserName(httpServletRequest)));
    }

    @PostMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> add(@PathVariable Long id, @ModelAttribute CommentDto comment, HttpServletRequest httpServletRequest) throws IOException {
        commentService.add(id, comment, extractUserName(httpServletRequest));
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(@RequestParam Long eventId, @ModelAttribute CommentDto comment, @PathVariable Long id, HttpServletRequest httpServletRequest) throws IOException {
        commentService.update(eventId, comment, id, extractUserName(httpServletRequest));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestParam Long eventId, @PathVariable Long id, HttpServletRequest httpServletRequest) {
        commentService.delete(eventId, id, extractUserName(httpServletRequest));
        return ResponseEntity.ok().build();
    }

    private String extractUserName(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        return username;
    }
}
