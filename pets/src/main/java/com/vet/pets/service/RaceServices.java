package com.vet.pets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.pets.dto.RaceDTO;
import com.vet.pets.entities.Breeds;
import com.vet.pets.entities.Species;
import com.vet.pets.repository.RaceRepository;
import com.vet.pets.repository.SpeciesRepository;

import jakarta.transaction.Transactional;

@Service
public class RaceServices {

    @Autowired
    private RaceRepository breedRepository;
    @Autowired
    private SpeciesRepository specieRepository;

    @Transactional
    public Breeds createBreed(RaceDTO dto){
        try{
            Species specie = specieRepository.findById(dto.id_specie())
            .orElseThrow(() -> new RuntimeException("Espécie não encontrada com o ID: " + dto.id_specie()));

            Breeds newBreed = breedRepository.save(new Breeds(null, dto.name(), specie));
            return newBreed;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public  Optional<Breeds> findBreedByName(String name){
        try{
            Optional<Breeds> breed = breedRepository.findByName(name);
            return breed;

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Breeds> listBreed(Optional<Long> id_specie){
        try{
            if(id_specie.isPresent()) {
                List<Breeds> breeds = breedRepository.findRaceBySpecieId(id_specie.get());
                return breeds;
            }

            List<Breeds> breeds = breedRepository.findAll();
            return breeds;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Breeds findBreed(Long id){
        try{
            Optional<Breeds> breed = breedRepository.findById(id);
            if(breed.isEmpty()) {
                return null;
            }

            return breed.get();

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteBreed(Long id){
        try{
            breedRepository.deleteById(id);
         } catch (Exception e){
             throw new RuntimeException(e.getMessage());
         }
    }


    public Breeds updateBreedById(Long id, RaceDTO dto){

        Species specie = specieRepository.findById(dto.id_specie())
        .orElseThrow(() -> new RuntimeException("Espécie não encontrada com o ID: " + dto.id_specie()));

        try{
            Optional<Breeds> breed = breedRepository.findById(id);
            if(breed.isEmpty()) {
                return null;
            }
          
            Breeds newBreed = breedRepository.save(new Breeds(id, dto.name(), specie));
            return newBreed;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
}
