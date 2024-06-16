package com.vet.pets.service;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vet.pets.dto.WorkerCreateDTO;
import com.vet.pets.entities.Worker;
import com.vet.pets.repository.WorkerRepository;

import jakarta.transaction.Transactional;
 
@Service
public class WorkerServices {
    @Autowired
    private WorkerRepository workerRepository;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Transactional
    public Worker createWorker(WorkerCreateDTO dto){
        try{
            Worker newWorker = new Worker();
            newWorker.setName(dto.name());
            newWorker.setCpf(dto.cpf());
            newWorker.setPhone(dto.phone());
            newWorker.setUsername(dto.username());
            newWorker.setPasswordd(dto.passwordd());
            newWorker.setUserLevel(dto.userLevel());
            newWorker.setActive(dto.active());
            newWorker.setEmail(dto.email());
            newWorker.setAddress(dto.address());
            newWorker.setFunctionn(dto.functionn());
            
            Worker savedWorker = workerRepository.save(newWorker);

            return savedWorker;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
 
    public List<Worker> listWorkers(){
        try{
            List<Worker> Worker = workerRepository.findAll();
            return Worker;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
 
    public Worker findWorker(Long id){
        try{
            Optional<Worker> worker = workerRepository.findById(id);
            if(worker.isEmpty()) {
                return null;
            }
 
            return worker.get();
 
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
 
    public  Optional<Worker> findWorkerByName(String name){
        try{
            Optional<Worker> Worker = workerRepository.findByName(name);
            return Worker;
 
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
 
    public void deleteWorker(Long id){
        try{
            workerRepository.deleteById(id);
         } catch (Exception e){
             throw new RuntimeException(e.getMessage());
         }
    }
 
    public Worker updateWorkerById(Long id, WorkerCreateDTO dto){
        try{
            Optional<Worker> worker = workerRepository.findById(id);
            if(worker.isEmpty()) {
                return null;
            }
            Worker newWorker = new Worker();
            newWorker.setId(id);
            newWorker.setName(dto.name());
            newWorker.setCpf(dto.cpf());
            newWorker.setPhone(dto.phone());
            newWorker.setUsername(dto.username());
            newWorker.setPasswordd(passwordEncoder.encode(dto.passwordd()));
            newWorker.setUserLevel(dto.userLevel());
            newWorker.setActive(dto.active());
            newWorker.setEmail(dto.email());
            newWorker.setAddress(dto.address());
            newWorker.setFunctionn(dto.functionn());
            
            Worker updatedWorker = workerRepository.save(newWorker);

            return updatedWorker;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}