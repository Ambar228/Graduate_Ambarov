package edu.bru.sponsormicroservce.service;

import edu.bru.sponsormicroservce.dto.SponsorDto;
import edu.bru.sponsormicroservce.model.Sponsor;

import java.io.IOException;
import java.util.List;

public interface SponsorService {
    List<Sponsor> get();
    void add(SponsorDto sponsorDto) throws IOException;
    void update(Long id, SponsorDto sponsorDto) throws IOException;
    void delete(Long id);
}
