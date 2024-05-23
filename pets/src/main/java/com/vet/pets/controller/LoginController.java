package com.vet.pets.controller;

import com.vet.pets.dto.WorkerLoggedDTO;
import com.vet.pets.dto.WorkerLoginDTO;
import com.vet.pets.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<WorkerLoggedDTO> login2(@RequestBody WorkerLoginDTO dto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginService.login(dto));
    }
}
