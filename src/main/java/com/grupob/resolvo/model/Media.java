package com.grupob.resolvo.model;

public class Media {
    private int idMedia;
    private int idIncidence;
    private byte[] data;

    public Media() {
    }

    public Media(int idMedia, int idIncidence, byte[] data) {
        this.idMedia = idMedia;
        this.idIncidence = idIncidence;
        this.data = data;
    }

    public int getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(int idMedia) {
        this.idMedia = idMedia;
    }

    public int getIdIncidence() {
        return idIncidence;
    }

    public void setIdIncidence(int idIncidence) {
        this.idIncidence = idIncidence;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
