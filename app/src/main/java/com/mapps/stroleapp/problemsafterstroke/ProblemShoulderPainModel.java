package com.mapps.stroleapp.problemsafterstroke;



public class ProblemShoulderPainModel {

    private String id ;
    private String email ;
    private int days ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmial() {
        return email;
    }

    public void setEmial(String email) {
        this.email = email;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public ProblemShoulderPainModel(String id, String email, int days) {
        this.id = id;
        this.email = email;
        this.days = days;
    }
}
