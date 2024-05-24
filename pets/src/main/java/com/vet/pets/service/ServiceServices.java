package com.vet.pets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.pets.dto.ServiceDTO;
import com.vet.pets.entities.Services;
import com.vet.pets.repository.ServiceRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceServices {

    @Autowired
    private ServiceRepository serviceRepository;

    @Transactional
    public Services createService(ServiceDTO dto){
        try{
            Services newService = serviceRepository.save(new Services(null, dto.name(), dto.price()));
            return newService;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public  Optional<Services> findServiceByName(String name){
        try{
            Optional<Services> service = serviceRepository.findByName(name);
            return service;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Services> listService(){
        try{
            List<Services> service = serviceRepository.findAll();
            return service;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Services findService(Long id){
        try{
            Optional<Services> service = serviceRepository.findById(id);
            if(service.isEmpty()) {
                return null;
            }

            return service.get();

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteService(Long id){
        try{
            serviceRepository.deleteById(id);
         } catch (Exception e){
             throw new RuntimeException(e.getMessage());
         }
    }

    public Services updateServiceById(Long id, ServiceDTO dto){
        try{
            Optional<Services> service = serviceRepository.findById(id);
            if(service.isEmpty()) {
                return null;
            }
            Services newService = serviceRepository.save(new Services(id, dto.name(), dto.price()));
            return newService;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
