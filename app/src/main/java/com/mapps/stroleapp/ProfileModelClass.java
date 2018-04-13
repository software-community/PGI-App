package com.mapps.stroleapp;

/**
 * Created by sachin on 08/04/18.
 */

public class ProfileModelClass {

    public ProfileModelClass(String gender, String contact, String date, String address, String occupation, String education, String name, String insurance,String relation,String role) {
        this.gender = gender;
        this.contact = contact;
        this.date = date;
        this.address = address;
        this.occupation = occupation;
        this.education = education;
        this.name = name;
        this.insurance = insurance;
        this.relation=relation;
        this.role=role;
    }
    public ProfileModelClass(String gender, String contact, String date, String address, String occupation, String education, String name,String insurance,String relation,String role,String previous) {
        this.gender = gender;
        this.contact = contact;
        this.date = date;
        this.address = address;
        this.occupation = occupation;
        this.education = education;
        this.name = name;
        this.relation=relation;
        this.role=role;
        this.previous=previous;
    }
    private String previous;
    private String relation;
    private String gender;
    private String contact;
    private String date;
    private String address;
    private String occupation;
    private String education;
    private String name;
    private String role;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    private String insurance;


}
