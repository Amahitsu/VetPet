package com.vet.pets.service;

import com.vet.pets.controller.ApiResponse;
import com.vet.pets.dto.WorkerLoggedDTO;
import com.vet.pets.dto.WorkerLoginDTO;
import com.vet.pets.entities.Worker;
import com.vet.pets.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public WorkerLoggedDTO login(WorkerLoginDTO dto){
        Optional<Worker> workerOptional = Optional.ofNullable(workerRepository.findByUsername(dto.username()));
        if(workerOptional.isPresent()){
            if(passwordEncoder.matches(dto.password(), workerOptional.get().getPassword())){
                return new WorkerLoggedDTO(workerOptional.get().getUsername(), workerOptional.get().getFunction(), workerOptional.get().getUserLevel());
            } else{
                throw new RuntimeException("The password: " + dto.password() + "don't match");
            }
        } else{
            throw new RuntimeException("The username: " + dto.username() + " not found!");
        }
    }
}
