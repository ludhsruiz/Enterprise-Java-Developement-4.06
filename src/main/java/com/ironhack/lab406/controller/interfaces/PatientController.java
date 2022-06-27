package com.ironhack.lab406.controller.interfaces;

import com.ironhack.lab406.enums.Status;
import com.ironhack.lab406.models.Patient;

import java.util.List;

public interface PatientController {

    // 2. Create a route to get patients by name
    public List<Patient> findByName(String name);

    // 3. Create a route to get a patient by patient_id
    public Patient findByPatientId(Integer id);

    // 4. Create a route to get patients by doctor
    public List<Patient> findByDoctorName(String admittedBy);

    // 5. Create a route to get patients date of birth within a specified range
    public List<Patient> findByDateOfBirthBetween(String startDate, String dateEnd);

    // 6. Create a route to get patients by department that their admitting doctor is in
    public List<Patient> findByDoctorDepartment(String department);

    // 7. Create a route to get all patients with a doctor whose status is OFF
    public List<Patient> findByDoctorStatusOff(Status status);

    // 8. Create a route to add a new patient
    public Patient addNewPatient(Patient patient);

    // 9. Create a patient's information (the user should be able to update any patient information through this route)
    public void updateAnyPatientInformation(Integer patientId, Patient patient);

}