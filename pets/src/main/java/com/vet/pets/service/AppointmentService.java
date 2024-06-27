package com.vet.pets.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.pets.dto.AppointmentDTO;
import com.vet.pets.dto.SlotDTO;
import com.vet.pets.entities.Animals;
import com.vet.pets.entities.Appointments;
import com.vet.pets.entities.Customer;
import com.vet.pets.entities.Medicines;
import com.vet.pets.entities.Services;
import com.vet.pets.entities.Vaccines;
import com.vet.pets.entities.Worker;
import com.vet.pets.repository.AnimalRepository;
import com.vet.pets.repository.AppointmentRepository;
import com.vet.pets.repository.CustomerRepository;
import com.vet.pets.repository.MedicinesRepository;
import com.vet.pets.repository.ServiceRepository;
import com.vet.pets.repository.VaccinesRepository;
import com.vet.pets.repository.WorkerRepository;

import jakarta.transaction.Transactional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private VaccinesRepository vaccineRepository;
    @Autowired
    private MedicinesRepository medicineRepository;

    @Transactional
    public Appointments createAppointment(AppointmentDTO dto) {
        try {
            Customer customer = customerRepository.findById(dto.id_customer())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + dto.id_customer()));
            Animals animal = animalRepository.findById(dto.id_animals())
                    .orElseThrow(() -> new RuntimeException("Animal não encontrada com o ID: " + dto.id_animals()));
            Worker worker = workerRepository.findById(dto.id_workers())
                    .orElseThrow(() -> new RuntimeException("Trabalhador não encontrado com o ID: " + dto.id_workers()));
            Services service = serviceRepository.findById(dto.id_services())
                    .orElseThrow(() -> new RuntimeException("Serviço não encontrado com o ID: " + dto.id_services()));

            Appointments newAppointment = new Appointments();
            newAppointment.setStart_time(dto.start_time());
            newAppointment.setFinish_time(dto.finish_time());
            newAppointment.setDate(dto.date());
            newAppointment.setObservation(dto.observation());
            newAppointment.setCustomer(customer);
            newAppointment.setAnimal(animal);
            newAppointment.setWorker(worker);
            newAppointment.setService(service);
            newAppointment.setVaccine(null);
            newAppointment.setMedicine(null);

            Appointments savedAppointment = appointmentRepository.save(newAppointment);

            return savedAppointment;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Appointments> findAppointmentById(Long id) {
        try {
            Optional<Appointments> appointment = appointmentRepository.findById(id);
            return appointment;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Appointments> listAppointments() {
        try {
            List<Appointments> appointment = appointmentRepository.findAll();
            return appointment;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Appointments findAppointment(Long id) {
        try {
            Optional<Appointments> appointment = appointmentRepository.findById(id);
            if (appointment.isEmpty()) {
                return null;
            }

            return appointment.get();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteAppointment(Long id) {
        try {
            appointmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public Appointments updateAppointmentById(Long id, AppointmentDTO dto) {
        try {
            Optional<Appointments> appointment = appointmentRepository.findById(id);
            if (appointment.isEmpty()) {
                return null;
            }

            Appointments existingAppointment = appointment.get();

            if (dto.start_time() != null) {
                existingAppointment.setStart_time(dto.start_time());
            }
            if (dto.finish_time() != null) {
                existingAppointment.setFinish_time(dto.finish_time());
            }
            if (dto.date() != null) {
                existingAppointment.setDate(dto.date());
            }
            if (dto.observation() != null) {
                existingAppointment.setObservation(dto.observation());
            }
            if (dto.id_customer() != null) {
                Customer customer = customerRepository.findById(dto.id_customer())
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + dto.id_customer()));
                existingAppointment.setCustomer(customer);
            }
            if (dto.id_animals() != null) {
                Animals animal = animalRepository.findById(dto.id_animals())
                        .orElseThrow(() -> new RuntimeException("Animal não encontrado com o ID: " + dto.id_animals()));
                existingAppointment.setAnimal(animal);
            }
            if (dto.id_workers() != null) {
                Worker worker = workerRepository.findById(dto.id_workers())
                        .orElseThrow(() -> new RuntimeException("Trabalhador não encontrado com o ID: " + dto.id_workers()));
                existingAppointment.setWorker(worker);
            }
            if (dto.id_services() != null) {
                Services service = serviceRepository.findById(dto.id_services())
                        .orElseThrow(() -> new RuntimeException("Serviço não encontrado com o ID: " + dto.id_services()));
                existingAppointment.setService(service);
            }
            if (dto.id_vaccines() != null ) {
                Vaccines vaccine = vaccineRepository.findById(dto.id_vaccines())
                        .orElseThrow(() -> new RuntimeException("Vacina não encontrado com o ID: " + dto.id_vaccines()));
                existingAppointment.setVaccine(vaccine);
            }
            if (dto.id_medicines() != null) {
                Medicines medicine = medicineRepository.findById(dto.id_medicines())
                        .orElseThrow(() -> new RuntimeException("Medicamento não encontrado com o ID: " + dto.id_medicines()));
                existingAppointment.setMedicine(medicine);
            }

            Appointments updatedAppointment = appointmentRepository.save(existingAppointment);

            return updatedAppointment;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<SlotDTO> availableSlots(Date date) {

        // Lista de slots disponíveis inicialmente
        List<SlotDTO> availableSlots = new ArrayList<>();
        for (int hour = 8; hour < 18; hour++) {
            availableSlots.add(new SlotDTO(hour + ":00", hour + ":30"));
            availableSlots.add(new SlotDTO(hour + ":30", (hour + 1) + ":00"));
        }

        try {
            // Busca no banco de dados por compromissos agendados para a data especificada
            List<Appointments> appointments = appointmentRepository.findByDate(date);

            // Remove os slots ocupados da lista de slots disponíveis
            for (Appointments appointment : appointments) {
                String startTime = appointment.getStart_time();
                String endTime = appointment.getFinish_time();

                // Cria um slot a partir do horário do compromisso para comparar e remover da lista
                SlotDTO slot = new SlotDTO(startTime, endTime);

                // Itera sobre os slots disponíveis e remove o slot ocupado, se existir
                for (int i = 0; i < availableSlots.size(); i++) {
                    SlotDTO availableSlot = availableSlots.get(i);
                    if (slot.equals(availableSlot)) {
                        availableSlots.remove(i);
                        break; // Interrompe o loop assim que o slot for removido
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return availableSlots;
    }

    public List<SlotDTO> listDate(Date date) {
        try {
            List<Appointments> appointments = appointmentRepository.findAll();
            List<SlotDTO> slots = new ArrayList<>();
            for (Appointments appointment : appointments) {
                SlotDTO slot = new SlotDTO(appointment.getStart_time(), appointment.getFinish_time());
                slots.add(slot);
            }
            return slots;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
