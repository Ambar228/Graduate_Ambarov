package edu.bru.member.microservice.service;

import edu.bru.member.microservice.model.Result;

import java.util.List;

public interface ResultService {
    List<Result> get();
    List<Result> getWinners(Long eventId);
    void add(Result sponsor);
    void update(Long id, Result sponsor);
    void delete(Long id);
}
