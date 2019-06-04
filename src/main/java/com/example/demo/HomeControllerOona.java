package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControllerOona {
    @Autowired
    InterviewRepository interviewRepository;

    @RequestMapping("/interviews")
    public String listMessages(Model model){
        model.addAttribute("interviews", interviewRepository.findAll());
        return "interviewList";
    }
}
