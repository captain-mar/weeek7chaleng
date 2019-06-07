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

    private String jobQuest1 = "one";
    private String jobQuest2 = "two";
    private String jobQuest3 = "three";

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;

    private String transcript;



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
    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getAnswer5() {
        return answer5;
    }

    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }

    public String getAnswer6() {
        return answer6;
    }

    public void setAnswer6(String answer6) {
        this.answer6 = answer6;
    }

    private String answer6;
}
