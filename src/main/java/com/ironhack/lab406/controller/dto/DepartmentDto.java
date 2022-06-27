package com.ironhack.lab406.controller.dto;
import com.sun.istack.NotNull;

public class DepartmentDto {
    @NotNull
    private String department;

    public DepartmentDto() {
    }

    public DepartmentDto(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
