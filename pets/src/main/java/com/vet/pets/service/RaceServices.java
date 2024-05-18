package com.vet.pets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vet.pets.dto.RaceDTO;
import com.vet.pets.entities.Customer;
import com.vet.pets.entities.Races;
import com.vet.pets.entities.Species;
import com.vet.pets.repository.RaceRepository;
import com.vet.pets.repository.SpeciesRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RaceServices {

    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private SpeciesRepository specieRepository;

    @Transactional
    public Races createRace(RaceDTO dto){
        try{
            Species specie = specieRepository.findById(dto.id_specie())
            .orElseThrow(() -> new RuntimeException("Espécie não encontrada com o ID: " + dto.id_specie()));

            Races newRace = raceRepository.save(new Races(null, dto.name(), specie));
            return newRace;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public  Optional<Races> findRaceByName(String name){
        try{
            Optional<Races> race = raceRepository.findByName(name);
            return race;

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Races> listRace(){
        try{
            List<Races> race = raceRepository.findAll();
            return race;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Races findRace(Long id){
        try{
            Optional<Races> race = raceRepository.findById(id);
            if(race.isEmpty()) {
                return null;
            }

            return race.get();

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteRace(Long id){
        try{
            raceRepository.deleteById(id);
         } catch (Exception e){
             throw new RuntimeException(e.getMessage());
         }
    }


    public Races updateRaceById(Long id, RaceDTO dto){

        Species specie = specieRepository.findById(dto.id_specie())
        .orElseThrow(() -> new RuntimeException("Espécie não encontrada com o ID: " + dto.id_specie()));

        try{
            Optional<Races> race = raceRepository.findById(id);
            if(race.isEmpty()) {
                return null;
            }
          
            Races newRace = raceRepository.save(new Races(id, dto.name(), specie));
            return newRace;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
}
