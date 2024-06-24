package com.vet.pets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.pets.dto.InServiceDTO;
import com.vet.pets.entities.Appointments;
import com.vet.pets.entities.InService;
import com.vet.pets.entities.Medicines;
import com.vet.pets.entities.Services;
import com.vet.pets.entities.Vaccines;
import com.vet.pets.repository.AppointmentRepository;
import com.vet.pets.repository.InServiceRepository;
import com.vet.pets.repository.MedicinesRepository;
import com.vet.pets.repository.ServiceRepository;
import com.vet.pets.repository.VaccinesRepository;

import jakarta.transaction.Transactional;

@Service
public class InServiceServices {

    @Autowired
    private InServiceRepository inServiceRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private VaccinesRepository vaccinesRepository;
    @Autowired
    private MedicinesRepository medicinesRepository;

    @Transactional
    public InService createInService(InServiceDTO dto) {
        try {
            Appointments appointment = appointmentRepository.findById(dto.id_appointment())
                    .orElseThrow(
                            () -> new RuntimeException("Agendamento não encontrado com o ID: " + dto.id_appointment()));
            Services service = serviceRepository.findById(dto.id_service())
                    .orElseThrow(
                            () -> new RuntimeException("Serviço não encontrao com o ID: " + dto.id_service()));
            Medicines medicine = medicinesRepository.findById(dto.id_medicine())
                    .orElseThrow(
                            () -> new RuntimeException("Medicamento não encontrada com o ID: " + dto.id_medicine()));
            Vaccines vaccine = vaccinesRepository.findById(dto.id_vaccine())
                    .orElseThrow(() -> new RuntimeException("Vacina não encontrada com o ID: " + dto.id_vaccine()));

            InService newInService = inServiceRepository
                    .save(new InService(null, service, appointment, medicine, vaccine, dto.observation()));
            return newInService;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<InService> listInService(Optional<Long> id_medicine, Optional<Long> id_vaccine) {
        try {
            if (id_medicine.isPresent()) {
                List<InService> inService = inServiceRepository.findInServiceByMedicineId(id_medicine.get());
                return inService;
            }
            if (id_vaccine.isPresent()) {
                List<InService> inService = inServiceRepository.findInServiceByVaccineId(id_vaccine.get());
                return inService;
            }

            List<InService> inService = inServiceRepository.findAll();
            return inService;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public InService findInService(Long id) {
        try {
            Optional<InService> inService = inServiceRepository.findById(id);
            if (inService.isEmpty()) {
                return null;
            }

            return inService.get();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteInService(Long id) {
        try {
            inServiceRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public InService updateInServiceById(Long id, InServiceDTO dto) {

        Appointments appointment = appointmentRepository.findById(dto.id_appointment())
                .orElseThrow(
                        () -> new RuntimeException("Agendamento não encontrado com o ID: " + dto.id_appointment()));
        Services service = serviceRepository.findById(dto.id_service())
                .orElseThrow(
                        () -> new RuntimeException("Serviço não encontrao com o ID: " + dto.id_service()));
        Medicines medicine = medicinesRepository.findById(dto.id_medicine())
                .orElseThrow(
                        () -> new RuntimeException("Medicamento não encontrada com o ID: " + dto.id_medicine()));
        Vaccines vaccine = vaccinesRepository.findById(dto.id_vaccine())
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada com o ID: " + dto.id_vaccine()));

        try {
            Optional<InService> inService = inServiceRepository.findById(id);
            if (inService.isEmpty()) {
                return null;
            }

            InService newInService = inServiceRepository
                    .save(new InService(id, service, appointment, medicine, vaccine, dto.observation()));
            return newInService;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
