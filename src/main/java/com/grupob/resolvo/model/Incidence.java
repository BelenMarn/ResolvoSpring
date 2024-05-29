package com.grupob.resolvo.model;

import com.grupob.resolvo.model.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Incidence {
    private int id_incidence;
    private int id_client;
    private String clientName;
    private int id_worker;
    private String workerName;
    private String device;
    private String brand;
    private String model;
    private String location;
    private String reason;
    private LocalDateTime open_date;
    private Status status;
    private String technical_report;
    private LocalDateTime close_date;
    private byte[] digital_sign;
    private ArrayList<Media> media;

    public Incidence() {
    }

    public Incidence(int id_incidence, int id_client, String clientName, int id_worker, String workerName, String device, String brand, String model, String location, String reason, LocalDateTime open_date, Status status, String technical_report, LocalDateTime close_date, byte[] digital_sign, ArrayList<Media> media) {
        this.id_incidence = id_incidence;
        this.id_client = id_client;
        this.clientName = clientName;
        this.id_worker = id_worker;
        this.workerName = workerName;
        this.device = device;
        this.brand = brand;
        this.model = model;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getOpen_date() {
        return open_date;
    }

    public void setOpen_date(LocalDateTime open_date) {
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

    public LocalDateTime getClose_date() {
        return close_date;
    }

    public void setClose_date(LocalDateTime close_date) {
        this.close_date = close_date;
    }

    public byte[] getDigital_sign() {
        return digital_sign;
    }

    public void setDigital_sign(byte[] digital_sign) {
        this.digital_sign = digital_sign;
    }

    public ArrayList<Media> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<Media> media) {
        this.media = media;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }
}
