package com.mapps.stroleapp.problemsafterstroke;



public class ProblemUrinaryCatheterModel {
    private String id ;
    private String email ;
    private int duration ;
    private String date ;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ProblemUrinaryCatheterModel(String id, String email, int duration, String date) {
        this.id = id;
        this.email = email;
        this.duration = duration;
        this.date = date;
    }
}
