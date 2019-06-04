package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 2)
    private String date;

    @NotNull
    @Size(min = 2)
    private String intTime;

    @NotNull
    @Size(min = 2)
    private String intLocation;

//    @OneToOne(mappedBy = "job",cascade = CascadeType.ALL)
//    private Job job;

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

    public String getIntLocation() {
        return intLocation;
    }

    public void setIntLocation(String intLocation) {
        this.intLocation = intLocation;
    }

//    public Job getJob() {
//        return job;
//    }
//
//    public void setJob(Job job) {
//        this.job = job;
//    }
}
