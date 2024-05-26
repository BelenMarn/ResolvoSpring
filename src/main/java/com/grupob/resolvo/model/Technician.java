package com.grupob.resolvo.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Technician {
    private int id_worker;
    private String name;
    private String surname;
    private String street;
    private String postal_code;
    private String city;
    private String province;
    private String phone;
    private String dni;
    private String email;
    private Date birthday_date;
    private Especializacion specialization;


    public Technician() {
    }

    public Technician(int id_worker, String name, String surname, String street, String postal_code, String city, String province, String phone, String dni, String email, Date birthday_date, Especializacion specialization) {
        this.id_worker = id_worker;
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.postal_code = postal_code;
        this.city = city;
        this.province = province;
        this.phone = phone;
        this.dni = dni;
        this.email = email;
        this.birthday_date = birthday_date;
        this.specialization = specialization;
    }

    public int getId_worker() {
        return id_worker;
    }

    public void setId_worker(int id_worker) {
        this.id_worker = id_worker;
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

    public Date getBirthday_date() {
        return birthday_date;
    }

    public void setBirthday_date(Date birthday_date) {
        this.birthday_date = birthday_date;
    }

    public Especializacion getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Especializacion specialization) {
        this.specialization = specialization;
    }
}