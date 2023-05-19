package com.example.lockar.Classes;

import java.io.Serializable;

public class ListAgendamentos implements Serializable {

    private Integer id;
    private Integer cadastroId;
    private String Nome;
    private String Modelo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCadastroId() {
        return cadastroId;
    }

    public void setCadastroId(Integer cadastroId) {
        this.cadastroId = cadastroId;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    private String Status;


    @Override
    public String toString(){
        return Nome + ", " + Modelo + ", "+ Status;
    }
}
