package com.grupob.resolvo.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Incidence {
    private int id_incidence;
    private int id_worker;
    private String device;
    private String brand;
    private String model;
    private String reason;
    private String open_date;
    private Status status;
    private String technical_report;
    private String close_date;
    private Byte[] digital_sign;
    private ArrayList<Byte[]> media;

    public Incidence() {
    }

    public Incidence(int id_incidence, int id_worker, String device, String brand, String model, String reason, String open_date, Status status, String technical_report, String close_date, Byte[] digital_sign, ArrayList<Byte[]> media) {
        this.id_incidence = id_incidence;
        this.id_worker = id_worker;
        this.device = device;
        this.brand = brand;
        this.model = model;
        this.reason = reason;
        this.open_date = open_date;
        this.status = status;
        this.technical_report = technical_report;
        this.close_date = close_date;
        this.digital_sign = digital_sign;
        this.media = media;
    }

    public int getId_incidence() {
        return id_incidence;
    }

    public void setId_incidence(int id_incidence) {
        this.id_incidence = id_incidence;
    }

    public int getId_worker() {
        return id_worker;
    }

    public void setId_worker(int id_worker) {
        this.id_worker = id_worker;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOpen_date() {
        return open_date;
    }

    public void setOpen_date(String open_date) {
        this.open_date = open_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTechnical_report() {
        return technical_report;
    }

    public void setTechnical_report(String technical_report) {
        this.technical_report = technical_report;
    }

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }

    public Byte[] getDigital_sign() {
        return digital_sign;
    }

    public void setDigital_sign(Byte[] digital_sign) {
        this.digital_sign = digital_sign;
    }

    public ArrayList<Byte[]> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<Byte[]> media) {
        this.media = media;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Incidence incidence = (Incidence) o;
        return id_incidence == incidence.id_incidence && id_worker == incidence.id_worker && device.equals(incidence.device) && brand.equals(incidence.brand) && model.equals(incidence.model) && reason.equals(incidence.reason) && open_date.equals(incidence.open_date) && status.equals(incidence.status) && technical_report.equals(incidence.technical_report) && close_date.equals(incidence.close_date) && Arrays.equals(digital_sign, incidence.digital_sign) && media.equals(incidence.media);
    }

    @Override
    public int hashCode() {
        int result = id_incidence;
        result = 31 * result + id_worker;
        result = 31 * result + device.hashCode();
        result = 31 * result + brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + reason.hashCode();
        result = 31 * result + open_date.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + technical_report.hashCode();
        result = 31 * result + close_date.hashCode();
        result = 31 * result + Arrays.hashCode(digital_sign);
        result = 31 * result + media.hashCode();
        return result;
    }
}
