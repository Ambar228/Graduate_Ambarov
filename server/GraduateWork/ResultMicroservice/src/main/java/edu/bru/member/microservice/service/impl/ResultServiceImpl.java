package edu.bru.member.microservice.service.impl;

import edu.bru.member.microservice.model.Result;
import edu.bru.member.microservice.repository.ResultRepository;
import edu.bru.member.microservice.service.ResultService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    @Override
    @Transactional
    public List<Result> get() {
        return (List<Result>) resultRepository.findAll();
    }

    @Override
    @Transactional
    public List<Result> getWinners(Long eventId) {
        List<Result> result = resultRepository.findByEventId(eventId);
        return result.stream().sorted(Comparator.comparing(Result::getPoints)).toList();
    }

    @Override
    @Transactional
    public void add(Result result) {
        resultRepository.save(result);
    }

    @Override
    @Transactional
    public void update(Long id, Result result) {
        Result sponsorForUpdate = resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result is not exist"));

        sponsorForUpdate.setEvent(result.getEvent());
        sponsorForUpdate.setPoints(result.getPoints());
        sponsorForUpdate.setRacer(result.getRacer());

        resultRepository.save(sponsorForUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        resultRepository.deleteById(id);
    }
}
