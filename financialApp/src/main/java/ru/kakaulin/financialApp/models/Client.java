package ru.kakaulin.financialApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "secondname")
    private String secondname;

    @Column(name = "codeword")
    private String codeword;


    public Client() {
    }


    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getCodeword() {
        return codeword;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondname(String secondName) {
        this.secondname = secondName;
    }

    public void setCodeword(String codeword) {
        this.codeword = codeword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
