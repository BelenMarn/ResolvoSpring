package com.grupob.resolvo.model.presupuesto;

import java.math.BigDecimal;

public class Budget {
    private int id_budget;
    private int id_incidence;
    private int id_client;
    private String description;
    private java.math.BigDecimal cost;
    private boolean accepted;

    public Budget() {
    }

    public Budget(int id_budget, int id_incidence, int id_client, String description, BigDecimal cost, boolean accepted) {
        this.id_budget = id_budget;
        this.id_incidence = id_incidence;
        this.id_client = id_client;
        this.description = description;
        this.cost = cost;
        this.accepted = accepted;
    }

    public int getId_budget() {
        return id_budget;
    }

    public void setId_budget(int id_budget) {
        this.id_budget = id_budget;
    }

    public int getId_incidence() {
        return id_incidence;
    }

    public void setId_incidence(int id_incidence) {
        this.id_incidence = id_incidence;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
