package com.grupob.resolvo.model.usuario_interno;

public class WorkerUser {
    private int id_worker;
    private String email;
    private String material;

    public WorkerUser() {
    }

    public WorkerUser(int id_worker, String email, String material) {
        this.id_worker = id_worker;
        this.email = email;
        this.material = material;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkerUser that = (WorkerUser) o;
        return id_worker == that.id_worker && email.equals(that.email) && material.equals(that.material);
    }

    @Override
    public int hashCode() {
        int result = id_worker;
        result = 31 * result + email.hashCode();
        result = 31 * result + material.hashCode();
        return result;
    }
}
