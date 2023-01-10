package com.example.restservice.entity;

public class BaseTranscation {

    private String id;

    private String userId;

    private String name;

    private int amount;

    private String ip;

    public BaseTranscation() {

    }

    public BaseTranscation(String id, String userId, String name, int amount, String ip) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.amount = amount;
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
