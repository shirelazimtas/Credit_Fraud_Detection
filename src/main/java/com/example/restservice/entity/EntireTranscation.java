package com.example.restservice.entity;

public class EntireTranscation {

    private BaseTranscation baseTranscation;

    private Transcation transcation;

    public EntireTranscation(BaseTranscation baseTranscation, Transcation transcation) {
        this.baseTranscation = baseTranscation;
        this.transcation = transcation;
    }

    public EntireTranscation() {

    }

    public BaseTranscation getBaseTranscation() {
        return baseTranscation;
    }

    public void setBaseTranscation(BaseTranscation baseTranscation) {
        this.baseTranscation = baseTranscation;
    }

    public Transcation getTranscation() {
        return transcation;
    }

    public void setTranscation(Transcation transcation) {
        this.transcation = transcation;
    }
}
