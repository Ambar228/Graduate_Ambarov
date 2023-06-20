package edu.bru.sponsormicroservce.service.impl;

import edu.bru.sponsormicroservce.dto.SponsorDto;
import edu.bru.sponsormicroservce.model.Sponsor;
import edu.bru.sponsormicroservce.repository.SponsorRepository;
import edu.bru.sponsormicroservce.service.SponsorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SponsorServiceImpl implements SponsorService {

    private final SponsorRepository sponsorRepository;

    @Override
    @Transactional
    public List<Sponsor> get() {
        return (List<Sponsor>) sponsorRepository.findAll();
    }

    @Override
    @Transactional
    public void add(SponsorDto sponsorDto) throws IOException {
        Sponsor sponsor = Sponsor.builder()
                .name(sponsorDto.getName())
                .contacts(sponsorDto.getContacts())
                .advertisingInformation(sponsorDto.getAdvertisingInformation())
                .build();

        if (!(sponsorDto.getLogo() == null)) {
            sponsor.setLogo(sponsorDto.getLogo().getBytes());
        }

        sponsorRepository.save(sponsor);
    }

    @Override
    @Transactional
    public void update(Long id, SponsorDto sponsorDto) throws IOException {
        Sponsor sponsorForUpdate = sponsorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sponsor is not exist"));

        if (!(sponsorDto.getLogo() == null)) {
            sponsorForUpdate.setLogo(sponsorDto.getLogo().getBytes());
        }

        sponsorForUpdate.setName(sponsorDto.getAdvertisingInformation());
        sponsorForUpdate.setContacts(sponsorDto.getContacts());
        sponsorForUpdate.setAdvertisingInformation(sponsorDto.getAdvertisingInformation());

        sponsorRepository.save(sponsorForUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        sponsorRepository.deleteById(id);
    }
}
