package com.vet.pets.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.pets.dto.AppointmentDTO;
import com.vet.pets.dto.SlotDTO;
import com.vet.pets.entities.Appointments;
import com.vet.pets.service.AppointmentService;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createAppointment(@RequestBody AppointmentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Appointments>("Created", appointmentServices.createAppointment(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Appointments>>> listAppointments() {
        List<Appointments> listAppointments = appointmentServices.listAppointments();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Appointments>>("Ok", listAppointments));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Appointments>> findAppointment(@PathVariable Long id) {
        Appointments appointment = appointmentServices.findAppointment(id);
        if (appointment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Appointments>("Ok", appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAppointment(@PathVariable Long id) {
        Appointments appointment = appointmentServices.findAppointment(id);
        if (appointment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        appointmentServices.deleteAppointment(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAppointmentById(@PathVariable Long id, @RequestBody AppointmentDTO dto) {
        Appointments appointment = appointmentServices.findAppointment(id);
        if (appointment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Animal não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Appointments>("Updated", appointmentServices.updateAppointmentById(id, dto)));
    }

    @GetMapping("/scheduled/{date}")
    public ResponseEntity<ApiResponse<List<SlotDTO>>> listDate(@PathVariable("date") String dateString) {
        // Converta a string da data para um objeto Date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Data inválida: " + dateString);
        }

        List<SlotDTO> listDates = appointmentServices.listDate(date);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<SlotDTO>>("Ok", listDates));
    }

    @GetMapping("/available/{date}")
    public ResponseEntity<ApiResponse<List<SlotDTO>>> availableSlots(@PathVariable("date") String dateString) {
        // Converta a string da data para um objeto Date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Data inválida: " + dateString);
        }

        List<SlotDTO> availableSlots = appointmentServices.availableSlots(date);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<SlotDTO>>("Ok", availableSlots));
    }

}
