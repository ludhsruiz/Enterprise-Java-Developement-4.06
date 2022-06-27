package com.ironhack.lab406.repository;

import com.ironhack.lab406.enums.Status;
import com.ironhack.lab406.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Employee findByEmployeeId(Integer employeeId);

    public List<Employee> findByStatus(Status status);

    public List<Employee> findByDepartment(String department);

}