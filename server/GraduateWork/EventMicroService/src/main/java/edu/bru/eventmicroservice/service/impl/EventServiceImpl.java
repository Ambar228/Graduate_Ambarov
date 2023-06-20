package edu.bru.eventmicroservice.service.impl;

import edu.bru.eventmicroservice.dto.EventDto;
import edu.bru.eventmicroservice.model.*;
import edu.bru.eventmicroservice.model.enums.StateEvent;
import edu.bru.eventmicroservice.repository.*;
import edu.bru.eventmicroservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final SponsorRepository sponsorRepository;
    private final UserRepository userRepository;
    private final RacerRepository racerRepository;
    private final TypeRepository typeRepository;
    private final TournamentRepository tournamentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Event> get() {
        return (List<Event>) eventRepository.findAll();
    }

    @Override
    @Transactional
    public Event getById(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Event getByName(String name) {
        return eventRepository.findByName(name);
    }

    @Override
    @Transactional
    public List<Event> getByState(String state) {
        return eventRepository.findByState(StateEvent.valueOf(state));
    }

    @Override
    @Transactional
    public void add(EventDto eventDto, String username) throws IOException {
        User user = userRepository.findByNumberPhone(username);

        if (user.getRole().name().equals("ADMIN")) {
            Event event = Event.builder()
                    .name(eventDto.getName())
                    .description(eventDto.getDescription())
                    .location(eventDto.getLocation())
                    .picture(eventDto.getPicture().getBytes())
                    .date(eventDto.getDate())
                    .state(StateEvent.valueOf(eventDto.getState().toUpperCase()))
                    .type(typeRepository.findByName(eventDto.getType()))
                    .build();
            eventRepository.save(event);
        }
    }

    @Override
    @Transactional
    public void addSponsor(Long id, Long sponsorId) {
        Sponsor sponsor = sponsorRepository.findById(sponsorId).orElseThrow(() -> new RuntimeException("Sponos is not exist"));
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event is not exist"));
        List<Sponsor> sponsors = event.getSponsors();
        List<Sponsor> newSponsors = new ArrayList<>(sponsors);
        newSponsors.add(sponsor);
        event.setSponsors(newSponsors);

        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void addTournament(Long id, Long tournamentId) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event is not exist"));
        event.setTournament(tournamentRepository.findById(tournamentId).get());
    }

    @Override
    @Transactional
    public void addRacer(Long id, Long racerId) {
        Racer racer = racerRepository.findById(racerId).orElseThrow(() -> new RuntimeException("Racer is not exist"));
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event is not exist"));

        List<Racer> racers = event.getRacers();
        List<Racer> newRacers = new ArrayList<>(racers);
        newRacers.add(racer);

        event.setRacers(newRacers);

        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void update(Long id, EventDto eventDto, String username) throws IOException {
        User user = userRepository.findByNumberPhone(username);

        if (user.getRole().name().equals("ADMIN")) {
            Event eventForUpdate = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Access denied"));
            eventForUpdate.setDate(eventDto.getDate());
            eventForUpdate.setDescription(eventDto.getDescription());
            eventForUpdate.setLocation(eventDto.getLocation());
            eventForUpdate.setType(typeRepository.findByName(eventDto.getType()));
            eventForUpdate.setName(eventDto.getName());
            eventForUpdate.setState(StateEvent.valueOf(eventDto.getState().toUpperCase()));
            eventForUpdate.setPicture(eventDto.getPicture().getBytes());
            eventRepository.save(eventForUpdate);
        }
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateStateToPasses() {
        LocalDate now = LocalDate.now();
        List<Event> events = (List<Event>) eventRepository.findAll();
        for (Event event : events) {
            if (event.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(now)) {
                event.setState(StateEvent.PASSES);
                eventRepository.save(event);
            }
        }
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateStateToCompleted() {
        LocalDate now = LocalDate.now();
        List<Event> events = (List<Event>) eventRepository.findAll();
        for (Event event : events) {
            if (event.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(now)) {
                event.setState(StateEvent.COMPLETED);
                eventRepository.save(event);
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long id, String username) {
        User user = userRepository.findByNumberPhone(username);

        if (user.getRole().name().equals("ADMIN")) {
            Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("event is not exist"));
            eventRepository.delete(event);
        }
    }
}
