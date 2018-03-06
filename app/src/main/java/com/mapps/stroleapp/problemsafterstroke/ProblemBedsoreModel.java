package com.mapps.stroleapp.problemsafterstroke;

/**
 * Created by frien_000 on 2/13/2018.
 */

public class ProblemBedsoreModel {
    private String id ;
    private String degree;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int day ;
    private String path ;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public ProblemBedsoreModel() {

    }

    public ProblemBedsoreModel(String id ,String email,int day, String path , String degree ) {
        this.email = email ;
        this.id = id ;
        this.day = day;
        this.path = path;
        this.degree = degree ;
    }


}
