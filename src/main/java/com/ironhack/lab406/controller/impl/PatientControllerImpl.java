package com.ironhack.lab406.controller.impl;

import com.ironhack.lab406.controller.interfaces.PatientController;
import com.ironhack.lab406.enums.Status;
import com.ironhack.lab406.models.Patient;
import com.ironhack.lab406.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


public class PatientControllerImpl implements PatientController {
    @Autowired
    private PatientServiceImpl patientService;

    // 1. Create a route to get all patients
    @GetMapping("/all-patients")
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    // 2. Create a route to get patients by name
    @GetMapping("/patients/{name}")
    public List<Patient> findByName(@PathVariable(name="name") String name) {
        return patientService.findByName(name);
    }

    // 3. Create a route to get a patient by patient_id
    @GetMapping("patient-by-id/{id}")
    public Patient findByPatientId(@PathVariable(name="id")Integer id) {
        return patientService.findByPatientId(id);
    }

    // 4. Create a route to get patients by doctor
    @GetMapping("patient-by-doctor/{name}")
    public List<Patient> findByDoctorName(@PathVariable(name="name") String name) {
        return patientService.findByDoctorName(name);
    }

    // 5. Create a route to get patients date of birth within a specified range
    @GetMapping("patient-by-range/{dateStart}/{dateEnd}")
    public List<Patient> findByDateOfBirthBetween(@PathVariable(name="dateStart") String startDate, @PathVariable(name="dateEnd") String dateEnd) {
        return patientService.findByDateOfBirthBetween(startDate, dateEnd);
    }

    // 6. Create a route to get patients by department that their admitting doctor is in
    @GetMapping("patients-by-department/{department}")
    public List<Patient> findByDoctorDepartment(@PathVariable(name="department")String department) {
        return patientService.findByDoctorDepartment(department);
    }

    // 7. Create a route to get all patients with a doctor whose status is OFF
    @GetMapping("patients-by-doctor-status/{status}")
    public List<Patient> findByDoctorStatusOff(@PathVariable(name="status") Status status) {
        return patientService.findByDoctorStatusOff(status);
    }


    ///// 2.04
    // 1. Create a route to add a new patient
    @PostMapping("/patient")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient addNewPatient(@RequestBody @Valid Patient patient) {
        return patientService.addNewPatient(patient);
    }

    // 6. Create a patient's information (the user should be able to update any patient information through this route)
    @PutMapping("/patient-update/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnyPatientInformation(@PathVariable Integer patientId, @RequestBody @Valid Patient patient) {
        patientService.updateAnyPatientInformation(patientId, patient);
    }
}
