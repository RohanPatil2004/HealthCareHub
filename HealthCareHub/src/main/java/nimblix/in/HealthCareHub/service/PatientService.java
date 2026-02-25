package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.AppointmentResponse;

import java.util.List;

public interface PatientService {
    // Task 170 - Get all appointments for a patient
    List<AppointmentResponse> getAppointmentsByPatient(Long patientId);
}
