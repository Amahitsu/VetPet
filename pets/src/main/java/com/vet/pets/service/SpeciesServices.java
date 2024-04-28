package com.vet.pets.service;

import com.vet.pets.dto.SpecieDTO;
import com.vet.pets.entities.Species;
import com.vet.pets.repository.SpeciesRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeciesServices {

    @Autowired
    private SpeciesRepository speciesRepository;

    public List<Species> listSpecies(){
        try{
            List<Species> species = speciesRepository.findAll();
            return species;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Species findSpecie(Long id){
        try{
            Optional<Species> specie = speciesRepository.findById(id);
            if(specie.isEmpty()) {
                return null;
            }

            return specie.get();

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public  Optional<Species> findSpecieByName(String name){
        try{
            Optional<Species> specie = speciesRepository.findByName(name);
            return specie;

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public Species createSpecie(SpecieDTO dto){
        try{
            Species newSpecie = speciesRepository.save(new Species(null, dto.name()));
            return newSpecie;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteSpecie(Long id){
        try{
            speciesRepository.deleteById(id);
         } catch (Exception e){
             throw new RuntimeException(e.getMessage());
         }
    }
    
    public Species updateSpecieById(Long id, SpecieDTO dto){
        try{
            Optional<Species> specie = speciesRepository.findById(id);
            if(specie.isEmpty()) {
                return null;
            }
            Species newSpecie = speciesRepository.save(new Species(id, dto.name()));
            return newSpecie;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
