package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class JobMethods {


    public List<String> SplitKeyWords(String key) { //updated to split up words

        List<String> items = Arrays.asList(key.split("\\s*,\\s*"));
        return items;
    }
}

