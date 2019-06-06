package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String date;

    private String intTime;

    private String behQuest1 = "Tell us a bit about yourself";
    private String behQuest2 = "Where do you see yourself 5 years";
    private String behQuest3 = "Give me an example of a time you faced " +
            "a conflict while working on a team. How did you handle that?";

    private String jobQuest1 = "";
    private String jobQuest2 = "";
    private String jobQuest3 = "";

    @OneToOne
    private Job job;

    public Interview() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIntTime() {
        return intTime;
    }

    public void setIntTime(String intTime) {
        this.intTime = intTime;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getBehQuest1() {
        return behQuest1;
    }

    public String getBehQuest2() {
        return behQuest2;
    }

    public String getBehQuest3() {
        return behQuest3;
    }

    public String getJobQuest1() {
        return jobQuest1;
    }

    public String getJobQuest2() {
        return jobQuest2;
    }

    public String getJobQuest3() {
        return jobQuest3;
    }
}
