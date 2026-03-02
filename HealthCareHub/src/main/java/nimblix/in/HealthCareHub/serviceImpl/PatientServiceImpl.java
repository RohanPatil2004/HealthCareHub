package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.response.AppointmentResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<AppointmentResponse> getAppointmentsByPatient(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElse(null);
         if(patient==null) return null;
        List<Appointment> appointments =
                patientRepository.findAppointmentsByPatientId(patientId);

        return appointments.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AppointmentResponse mapToResponse(Appointment appointment) {

        AppointmentResponse response = new AppointmentResponse();

        // Appointment info
        response.setAppointmentId(appointment.getId());
        response.setAppointmentDateTime(appointment.getAppointmentDateTime());
        response.setStatus(appointment.getStatus());

        // Patient info
        Patient patient = appointment.getPatient();
        response.setPatientId(patient.getId());
        response.setPatientName(patient.getName());
        response.setPatientPhone(patient.getPhone());

        // Doctor info
        Doctor doctor = appointment.getDoctor();
        response.setDoctorId(doctor.getId());
        response.setDoctorName("Dr. " + doctor.getName());

        if (doctor.getSpecialization() != null) {
            response.setDoctorSpecialization(
                    doctor.getSpecialization().getName());
        } else {
            response.setDoctorSpecialization("General");
        }

        return response;
    }
}
