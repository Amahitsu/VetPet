package com.vet.pets.service;

import com.vet.pets.controller.ApiResponse;
import com.vet.pets.dto.WorkerCreateDTO;
import com.vet.pets.entities.Worker;
import com.vet.pets.repository.WorkerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WorkerServices {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public ApiResponse createWorker(WorkerCreateDTO dto){
        try{
            workerRepository.save(new Worker(null, dto.name(), dto.cpf(), dto.phone(),
                    dto.address(), dto.function(), dto.username(), passwordEncoder.encode(dto.password()), dto.userLevel(), dto.active()));
            return new ApiResponse("Created!");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
