package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Job ID. Long auto generated

    @NotNull
    private String positionTitle;// Would be the title i.e. "Junior Java dev"

    private String startDate; // Start date for the job

    private String endDate; //if this is a contract position / this would be the end date for the contract

    @NotNull
    private String typeOfJob;//Contract, Part-time, Full-time

    @NotNull
    private Double salary; // How much you will get paid

    private String location; // What city is the job in?

    @NotNull
    private String description; // What is the job description

    @NotNull
    private String keyWord; // key words would be used to match items against user resumes

   private String education; // Where did you go to school?

    /*
    Start Getters and Setters
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTypeOfJob() {
        return typeOfJob;
    }

    public void setTypeOfJob(String typeOfJob) {
        this.typeOfJob = typeOfJob;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

     /*
    End Getters and Setters
     */

     /*
     Start constructors
      */

    public Job(@NotNull String positionTitle, String startDate, String endDate, @NotNull String typeOfJob, @NotNull Double salary, String location, @NotNull String description, @NotNull String keyWord, String education) {
        this.positionTitle = positionTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.typeOfJob = typeOfJob;
        this.salary = salary;
        this.location = location;
        this.description = description;
        this.keyWord = keyWord;
        this.education = education;
    }

    public Job() {
    }

    /*
     End constructors
      */
}
