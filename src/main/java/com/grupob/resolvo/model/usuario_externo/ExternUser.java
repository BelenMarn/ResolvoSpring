package com.grupob.resolvo.model.usuario_externo;

public class ExternUser {
    private int id_client;
    private String email;
    private String material;

    public ExternUser() {
    }

    public ExternUser(int id_client, String email, String material) {
        this.id_client = id_client;
        this.email = email;
        this.material = material;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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
}
