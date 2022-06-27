package com.ironhack.lab406.service.impl;
import com.ironhack.lab406.enums.Status;
import com.ironhack.lab406.models.Employee;
import com.ironhack.lab406.repository.EmployeeRepository;
import com.ironhack.lab406.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void saveAll(List<Employee> employeeList) {
        employeeRepository.saveAll(employeeList);
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    // Create a route to get all doctors
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // Create a route to get doctor by employee_id
    public Employee findByEmployeeId(Integer id) {
        return employeeRepository.findByEmployeeId(id);
    }

    // Create a route to get doctors by status
    public List<Employee> findByStatus(Status status) {
        return employeeRepository.findByStatus(status);
    }

    // Create a route to get doctors by department
    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    // Create a route to add a new employee
    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Create a route to change an employee's status
    public void changeEmployeeStatus(Integer employeeId, Status status) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()){
            employee.get().setStatus(status);
            employeeRepository.save(employee.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

    // Create a route to update an employee's name
    public void changeEmployeeName(Integer employeeId, String name) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()){
            employee.get().setName(name);
            employeeRepository.save(employee.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

    // Create a route to update an employee's department
    public void changeEmployeeDepartment(Integer employeeId, String department) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()){
            employee.get().setDepartment(department);
            employeeRepository.save(employee.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

}
