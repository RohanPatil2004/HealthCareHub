package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


    Optional<Patient> findByUser_Email(String email);

    // Find patient by hospital
    java.util.List<Patient> findByHospital_Id(Long hospitalId);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId")
    List<Appointment> findAppointmentsByPatientId(@Param("patientId") Long patientId);
}
