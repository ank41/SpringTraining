package ru.kakaulin.financialApp.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;

public class ClientDTO {


    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @Nullable
    private String secondname;

    private String codeword;


    public String getCodeword() {
        return codeword;
    }

    public void setCodeword(String codeword) {
        this.codeword = codeword;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }
}
