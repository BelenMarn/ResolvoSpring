package com.grupob.resolvo.model.cliente;

import com.grupob.resolvo.model.enums.Contract;

public class Client {
    private int id_client;
    private String name;
    private String surname;
    private String street;
    private String postal_code;
    private String city;
    private String province;
    private String phone;
    private String dni;
    private String email;
    private Contract contract;
    private int num_incidents;

    public Client() {
    }

    public Client(int id_client, String name, String surname, String street, String postal_code, String city, String province, String phone, String dni, String email, Contract contract, int num_incidents) {
        this.id_client = id_client;
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.postal_code = postal_code;
        this.city = city;
        this.province = province;
        this.phone = phone;
        this.dni = dni;
        this.email = email;
        this.contract = contract;
        this.num_incidents = num_incidents;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getNum_incidents() {
        return num_incidents;
    }

    public void setNum_incidents(int num_incidents) {
        this.num_incidents = num_incidents;
    }
}
