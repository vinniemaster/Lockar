package com.example.lockar;

import java.io.Serializable;

public class Cadastro implements Serializable {

    private Integer Id;
    private String Nome;
    private Integer Telefone;
    private Integer CPF;
    private String Modelo;
    private  String Placa;
    private  String Cor;
    private String Carroceria;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Integer getTelefone() {
        return Telefone;
    }

    public void setTelefone(Integer telefone) {
        Telefone = telefone;
    }

    public Integer getCPF() {
        return CPF;
    }

    public void setCPF(Integer CPF) {
        this.CPF = CPF;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getCor() {
        return Cor;
    }

    public void setCor(String cor) {
        Cor = cor;
    }

    public String getCarroceria() {
        return Carroceria;
    }

    public void setCarroceria(String carroceria) {
        Carroceria = carroceria;
    }
}
