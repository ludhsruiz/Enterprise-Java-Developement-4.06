package com.ironhack.lab406.controller.impl;

import com.ironhack.lab406.controller.interfaces.EmployeeController;
import com.ironhack.lab406.controller.dto.DepartmentDto;
import com.ironhack.lab406.controller.dto.NameDto;
import com.ironhack.lab406.controller.dto.StatusDto;
import com.ironhack.lab406.enums.Status;
import com.ironhack.lab406.models.Employee;
import com.ironhack.lab406.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;


    // Create a route to get all doctors
    @GetMapping("/all-doctors")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // Create a route to get doctor by employee_id
    @GetMapping("/doctor-by-id/{id}")
    public Employee findByEmployeeId(@PathVariable(name="id")Integer id) {
        return employeeService.findByEmployeeId(id);
    }

    // Create a route to get doctors by status
    @GetMapping("/doctors-by-status/{status}")
    public List<Employee> findByStatus(@PathVariable(name="status") Status status) {
        return employeeService.findByStatus(status);
    }

    // Create a route to get doctors by department
    @GetMapping("/doctors-by-department/{department}")
    public List<Employee> findByDepartment(@PathVariable(name="department") String department) {
        return employeeService.findByDepartment(department);
    }



    // GET 2.04
    // 2. Create a route to add a new patient
    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addNewEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.addNewEmployee(employee);
    }


    // 3. Create a route to change an employee's status
    @PatchMapping("/employee-status/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeEmployeeStatus(@PathVariable Integer employeeId, @RequestBody @Valid StatusDto status){
        employeeService.changeEmployeeStatus(employeeId, status.getStatus());
    }

    // 4. Create a route to update an employee's name
    @PatchMapping("employee-name/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeEmployeeName(@PathVariable Integer employeeId, @RequestBody @Valid NameDto name) {
        employeeService.changeEmployeeName(employeeId, name.getName());
    }

    // 5. Create a route to update an employee's department
    @PatchMapping("/employee-department/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeEmployeeDepartment(@PathVariable Integer employeeId, @RequestBody @Valid DepartmentDto department) {
        employeeService.changeEmployeeDepartment(employeeId, department.getDepartment());
    }
}