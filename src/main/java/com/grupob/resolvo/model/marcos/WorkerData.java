package com.grupob.resolvo.model.marcos;

import com.grupob.resolvo.model.enums.Position;
import com.grupob.resolvo.model.enums.Specialization;

import java.util.Date;

public class WorkerData {
    private int idWorker;
    private String name;
    private String surname;
    private String street;
    private String postal_code;
    private String city;
    private String province;
    private String phone;
    private String dni;
    private String email;
    private java.sql.Date birthday_date;
    private Position position;
    private Specialization specialization;

    public WorkerData() {
    }

    public WorkerData(int idWorker, String name, String surname, String street, String postal_code, String city, String province, String phone, String dni, String email, java.sql.Date birthday_date, Position position, Specialization specialization) {
        this.idWorker = idWorker;
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
        this.position = position;
        this.specialization = specialization;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
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

    public java.sql.Date getBirthday_date() {
        return birthday_date;
    }

    public void setBirthday_date(java.sql.Date birthday_date) {
        this.birthday_date = birthday_date;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "WorkerData{" +
                "idWorker=" + idWorker +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", street='" + street + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", phone='" + phone + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", birthday_date=" + birthday_date +
                ", position=" + position +
                ", specialization=" + specialization +
                '}';
    }
}
