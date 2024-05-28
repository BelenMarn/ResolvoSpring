package com.grupob.resolvo.model.marcos;

public class InternUser {
    private int id_worker;
    private String email;
    private String material;
    private boolean firstTime;

    public InternUser() {
    }

    public InternUser(int id_worker, String email, String material, boolean firstTime) {
        this.id_worker = id_worker;
        this.email = email;
        this.material = material;
        this.firstTime = firstTime;
    }

    public int getId_worker() {
        return id_worker;
    }

    public void setId_worker(int id_worker) {
        this.id_worker = id_worker;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }
}
