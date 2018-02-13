package com.mapps.stroleapp.problemsafterstroke;

/**
 * Created by frien_000 on 2/13/2018.
 */

public class ProblemBadMouthOdorModel {

    private String id ;
    private String emial ;
    private int days ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public ProblemBadMouthOdorModel(String id, String emial, int days) {
        this.id = id;
        this.emial = emial;
        this.days = days;
    }
}
