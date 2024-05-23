package com.vet.pets.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vet.pets.dto.WorkerLoggedDTO;
import com.vet.pets.dto.WorkerLoginDTO;
import com.vet.pets.entities.Worker;
import com.vet.pets.exceptions.PasswordNotMatches;
import com.vet.pets.exceptions.UsernameNotFound;
import com.vet.pets.repository.WorkerRepository;

@Service
public class LoginService {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public WorkerLoggedDTO login(WorkerLoginDTO dto){
        Optional<Worker> workerOptional = Optional.ofNullable(workerRepository.findByUsername(dto.username()));
        if(workerOptional.isPresent()){
            if(passwordEncoder.matches(dto.password(), workerOptional.get().getPasswordd())){
                return new WorkerLoggedDTO(workerOptional.get().getUsername(), workerOptional.get().getFunctionn(), workerOptional.get().getUserLevel(), workerOptional.get().getName());
            } else{
                throw new PasswordNotMatches("The password provided is incorrect!");
            }
        } else{
            throw new UsernameNotFound("The username: " + dto.username() + " not found!");
        }
    }
}
