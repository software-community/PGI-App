package com.mapps.stroleapp.problemsafterstroke;



public class ProblemLinkWeeknessModel {
    private String id ;
    private String email ;
    private String limbWeekness ;
    private int days ;

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

    public String getLimbWeekness() {
        return limbWeekness;
    }

    public void setLimbWeekness(String limbWeekness) {
        this.limbWeekness = limbWeekness;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public ProblemLinkWeeknessModel(String id, String email, String limbWeekness, int days) {
        this.id = id;
        this.email = email;
        this.limbWeekness = limbWeekness;
        this.days = days;
    }
}
