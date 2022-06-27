package com.ironhack.lab406.service.impl;

import com.ironhack.lab406.enums.Status;
import com.ironhack.lab406.models.Patient;
import com.ironhack.lab406.repository.PatientRepository;
import com.ironhack.lab406.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public void saveAll(List<Patient> patientList) {
        patientRepository.saveAll(patientList);
    }

    public void deleteAll() {
        patientRepository.deleteAll();
    }

    // Create a route to get all patients
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    // Create a route to get patients by name
    public List<Patient> findByName(String name) {
        return patientRepository.findByName(name);
    }

    // Create a route to get a patient by patient_id

    public Patient findByPatientId(Integer id) {
        return patientRepository.findByPatientId(id);
    }

    // Create a route to get patients by doctor

    public List<Patient> findByDoctorName( String name) {
        return patientRepository.findByDoctorName(name);
    }

    // Create a route to get patients date of birth within a specified range
    public List<Patient> findByDateOfBirthBetween(String startDate, String dateEnd) {
        return patientRepository.findByDateOfBirthBetween(LocalDate.parse(startDate), LocalDate.parse(dateEnd));
    }

    // Create a route to get patients by department that their admitting doctor is in
    public List<Patient> findByDoctorDepartment(String department) {
        return patientRepository.findByDoctorDepartment(department);
    }

    // Create a route to get all patients with a doctor whose status is OFF
    public List<Patient> findByDoctorStatusOff(Status status) {
        return patientRepository.findByDoctorStatusOff(status);
    }

    // Create a route to add a new patient
    public Patient addNewPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Create a patient's information (the user should be able to update any patient information through this route)
    public void updateAnyPatientInformation(Integer patientId, Patient patient){
        if (patientRepository.findById(patientId).isPresent()){
            patient.setPatientId(patientId);
            patientRepository.save(patient);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

}
