package com.example.demo;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String filename;
   // @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id")
   // private User user;
    private ArrayList<String> result = new ArrayList<>();





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ArrayList<String> getResult() {
        return result;
    }

    public void setResult(ArrayList<String> result) {
        this.result = result;
    }

    public Resume() {
    }
}
