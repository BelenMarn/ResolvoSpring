package com.grupob.resolvo.model;

public class Technician {
    private WorkerUser user;
    private String name;
    private String surname;
    private String street;
    private String postal_code;
    private String city;
    private String province;
    private String phone;
    private String dni;
    private String email;
    private String birthday_date;
    private String specialization;

    public Technician() {
    }

    public Technician(WorkerUser user, String name, String surname, String street, String postal_code, String city, String province, String phone, String dni, String email, String birthday_date, String specialization) {
        this.user = user;
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

    public WorkerUser getUser() {
        return user;
    }

    public void setUser(WorkerUser user) {
        this.user = user;
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

    public String getBirthday_date() {
        return birthday_date;
    }

    public void setBirthday_date(String birthday_date) {
        this.birthday_date = birthday_date;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Technician that = (Technician) o;
        return user.equals(that.user) && name.equals(that.name) && surname.equals(that.surname) && street.equals(that.street) && postal_code.equals(that.postal_code) && city.equals(that.city) && province.equals(that.province) && phone.equals(that.phone) && dni.equals(that.dni) && email.equals(that.email) && birthday_date.equals(that.birthday_date) && specialization.equals(that.specialization);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + postal_code.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + province.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + dni.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + birthday_date.hashCode();
        result = 31 * result + specialization.hashCode();
        return result;
    }
}