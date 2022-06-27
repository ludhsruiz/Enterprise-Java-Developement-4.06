package com.ironhack.lab406.controller.dto;
import com.sun.istack.NotNull;

public class NameDto {
    @NotNull
    private String name;

    public NameDto() {
    }

    public NameDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
