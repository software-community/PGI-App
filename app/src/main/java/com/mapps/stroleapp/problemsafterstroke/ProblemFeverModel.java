package com.mapps.stroleapp.problemsafterstroke;


public class ProblemFeverModel {
    private String id ;
    private String email ;
    private int duration ;
    private double temperature ;
    private String specifyMedicine ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }


    public String getSpecifyMedicine() {
        return specifyMedicine;
    }

    public void setSpecifyMedicine(String specifyMedicine) {
        this.specifyMedicine = specifyMedicine;
    }

    public ProblemFeverModel(String id, String email, int duration, double temperature, String specifyMedicine) {
        this.id = id;
        this.email = email;
        this.duration = duration;
        this.temperature = temperature;

        this.specifyMedicine = specifyMedicine;
    }
}
