package com.vet.pets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vet.pets.dto.RaceDTO;
import com.vet.pets.entities.Customer;
import com.vet.pets.entities.Races;
import com.vet.pets.repository.RaceRepository;

import jakarta.transaction.Transactional;

public class RaceServices {

    @Autowired
    private RaceRepository raceRepository;

    @Transactional
    public Races createRace(RaceDTO dto){
        try{
            Races newRace = raceRepository.save(new Races(null, dto.name(), dto.specieId());
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
        try{
            Optional<Races> race = raceRepository.findById(id);
            if(race.isEmpty()) {
                return null;
            }
            Customer newRace = raceRepository.save(new Races(id, dto.name(), dto.specieId() ));
            return newRace;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
}
