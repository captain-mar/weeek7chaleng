package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeControllerOona {
    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    UserService userService;

    @RequestMapping("/interviews")
    public String listMessages(Model model){
        model.addAttribute("interviews", interviewRepository.findAll());
        return "interviewList";
    }

    @GetMapping("/intRegister")
    public String InterviewReg(Model model){
        model.addAttribute("interview", new Interview());
        return "interviewRegistration";
    }

    @PostMapping("/intRegister")
    public String processInterviewReg( @ModelAttribute("interview") Interview interview, Model model){

//        if(result.hasErrors()){
//            return "interviewRegistration";
//        }
//
//        else {
            interviewRepository.save(interview);
            model.addAttribute("message", "Interview Created");
//        }
        return "redirect:/interviews";
    }

}
