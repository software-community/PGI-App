package com.mapps.stroleapp.problemsafterstroke;



public class ProblemOtherProblemsModel {
    String id ;
    String email ;
    int duration ;
    String problem1 ;
    String problem2 ;
    String problem3 ;

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

    public String getProblem1() {
        return problem1;
    }

    public void setProblem1(String problem1) {
        this.problem1 = problem1;
    }

    public String getProblem2() {
        return problem2;
    }

    public void setProblem2(String problem2) {
        this.problem2 = problem2;
    }

    public String getProblem3() {
        return problem3;
    }

    public void setProblem3(String problem3) {
        this.problem3 = problem3;
    }

    public ProblemOtherProblemsModel(String id, String email, int duration, String problem1, String problem2, String problem3) {
        this.id = id;
        this.email = email;
        this.duration = duration;
        this.problem1 = problem1;
        this.problem2 = problem2;
        this.problem3 = problem3;
    }
}
