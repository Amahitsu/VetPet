package com.vet.pets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.pets.dto.RaceDTO;
import com.vet.pets.entities.Races;
import com.vet.pets.entities.Species;
import com.vet.pets.repository.RaceRepository;
import com.vet.pets.repository.SpeciesRepository;

import jakarta.transaction.Transactional;

@Service
public class RaceServices {

    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private SpeciesRepository speciesRepository;

    @Transactional
    public Races createRace(RaceDTO dto) {
        try {
            Species species = speciesRepository.findById(dto.id_specie())
                    .orElseThrow(() -> new RuntimeException("Espécie não encontrada com o ID: " + dto.id_specie()));

            Races newRace = new Races();
            newRace.setName(dto.name());
            newRace.setSpecie(species);

            Races savedRace = raceRepository.save(newRace);

            return savedRace;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Races> findRaceByName(String name) {
        try {
            Optional<Races> race = raceRepository.findByName(name);
            return race;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Races> listRace() {
        try {
            List<Races> race = raceRepository.findAll();
            return race;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Races findRace(Long id) {
        try {
            Optional<Races> race = raceRepository.findById(id);
            if (race.isEmpty()) {
                return null;
            }

            return race.get();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteRace(Long id) {
        try {
            raceRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public Races updateRaceById(Long id, RaceDTO dto) {
        try {
            Optional<Races> raceOptional = raceRepository.findById(id);
            if (raceOptional.isEmpty()) {
                throw new RuntimeException("Raça não encontrada");
            }

            Races existingRace = raceOptional.get();

            Species species = speciesRepository.findById(dto.id_specie())
                    .orElseThrow(() -> new RuntimeException("Espécie não encontrada com o ID: " + dto.id_specie()));

            // Atualiza os dados da raça com base no DTO
            if (dto.name() != null) {
                existingRace.setName(dto.name());
            }
            if (dto.id_specie() != null) {
                existingRace.setSpecie(species);
            }

            Races updatedRace = raceRepository.save(existingRace);

            return updatedRace;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
