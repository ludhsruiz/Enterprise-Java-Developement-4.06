package com.ironhack.lab406.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.lab406.enums.Status;
import com.ironhack.lab406.models.Employee;
import com.ironhack.lab406.models.Patient;

import com.ironhack.lab406.service.interfaces.EmployeeService;
import com.ironhack.lab406.service.interfaces.PatientService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class PatientControllerImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PatientService patientService;


    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        LocalDate localdate = null;

        Employee employee1 = new Employee(123456, "cardiology", "Pepe Navarro", Status.ON);
        Employee employee2 = new Employee(654321, "pediatry", "Jaime Lobo", Status.OFF);
        employeeService.saveAll(List.of(employee1, employee2));

        Patient patient1 = new Patient("Bruce Wayne", localdate.of(2000, 2, 23), employee1);
        Patient patient2 = new Patient("Ben Johnson", localdate.of(2011, 6, 4), employee2);
        patientService.saveAll(List.of(patient1, patient2));

    }

    @AfterEach
    void tearDown() {
        patientService.deleteAll();
        employeeService.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        MvcResult result = mockMvc.perform(get("/all-patients")).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Bruce"));
        assertTrue(result.getResponse().getContentAsString().contains("Ben"));
    }

    @Test
    void findByName() throws Exception {
        MvcResult result = mockMvc.perform(get("/patients/Bruce Wayne")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Bruce"));
    }

    @Test
    void findByPatientId() throws Exception {
        List<Patient> patientList= patientService.findAll();
        Integer id = patientList.get(0).getPatientId();
        MvcResult result = mockMvc.perform(get("/patient-by-id/"+id)).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Bruce"));
    }

    @Test
    void findByDoctorName() throws Exception {
        MvcResult result = mockMvc.perform(get("/patient-by-doctor/Pepe Navarro")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Bruce"));
    }

    @Test
    void findByDateOfBirthBetween() throws Exception {
        String startDate="1999-01-01";
        String dateEnd="2001-01-01";
        MvcResult result = mockMvc.perform(get("/patient-by-range/"+startDate+"/"+dateEnd)).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Bruce"));
    }

    @Test
    void findByDoctorDepartment() throws Exception {
        MvcResult result = mockMvc.perform(get("/patients-by-department/cardiology")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Bruce"));
    }

    @Test
    void findByDoctorStatusOff() throws Exception {
        MvcResult result = mockMvc.perform(get("/patients-by-doctor-status/OFF")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Ben"));
    }

    @Test
    void addNewPatient() throws Exception {
        Employee employee3 = employeeService.findAll().get(0);
        Patient patient = new Patient("Rosa Melano", LocalDate.of(1995, 01,03), employee3);
        String body = objectMapper.writeValueAsString(patient);

        MvcResult result = mockMvc.perform(
                post("/patient")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Melano"));
    }

    @Test
    void updateAnyPatientInformation() throws Exception {
        Patient patient = patientService.findAll().get(0);
        Integer id = patient.getPatientId();
        patient.setName("Ramón García");
        String body = objectMapper.writeValueAsString(patient);
        System.out.println(body);
        MvcResult result = mockMvc.perform(
                put("/patient-update/"+id)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent()).andReturn();
    }
}