package com.ironhack.lab406.service.interfaces;

import com.ironhack.lab406.enums.Status;
import com.ironhack.lab406.models.Employee;

import java.util.List;


public interface EmployeeService {

    // Create a route to get all doctors
    public List<Employee> findAll();

    // Create a route to get doctor by employee_id
    public Employee findByEmployeeId(Integer employeeId);

    // Create a route to get doctors by status
    public List<Employee> findByStatus(Status status);

    // Create a route to get doctors by department
    public List<Employee> findByDepartment(String department);

    // Create a route to add a new employee
    public Employee addNewEmployee(Employee employee);

    // Create a route to change an employee's status
    public void changeEmployeeStatus(Integer employeeId, Status status);

    // Create a route to update an employee's name
    public void changeEmployeeName(Integer employeeId, String name);

    // Create a route to update an employee's department
    public void changeEmployeeDepartment(Integer employeeId, String department);

}
