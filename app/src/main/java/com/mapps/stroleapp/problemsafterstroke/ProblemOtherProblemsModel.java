package com.mapps.stroleapp.problemsafterstroke;



public class ProblemOtherProblemsModel {
    String id ;
    String email ;
    int duration1 ;
    int duration2 ;
    int duration3 ;
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

    public int getDuration1() {return duration1;}

    public void setDuration1(int duration1) {this.duration1 = duration1;}

    public int getDuration2() {return duration2;}

    public void setDuration2(int duration2) {this.duration2 = duration2;}

    public int getDuration3() {return duration3;}

    public void setDuration3(int duration3) {this.duration3 = duration3;}

    public ProblemOtherProblemsModel(String id, String email, Integer duration1 ,Integer duration2 , Integer duration3, String problem1, String problem2, String problem3) {
        this.id = id;
        this.email = email;
        this.duration1 = duration1;
        this.duration2 = duration2;
        this.duration3 = duration3;
        this.problem1 = problem1;
        this.problem2 = problem2;
        this.problem3 = problem3;
    }
}
