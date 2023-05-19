package com.example.lockar.Classes;

import java.io.Serializable;

public class Cadastro implements Serializable {

    private Integer Id;
    private String Nome;
    private String Telefone;
    private String CPF;
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

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
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

    @Override
    public String toString(){
        return Nome + ", " + Modelo + ", "+ Cor;
    }
}
