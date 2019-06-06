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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

@Controller
public class HomeControllerOona {
    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    UserService userService;

    @RequestMapping("/interviews")
    public String listMessages(Model model) {
        model.addAttribute("interviews", interviewRepository.findAll());
        return "interviewList";
    }

    @GetMapping("/intRegister")
    public String InterviewReg(Model model) {
        model.addAttribute("interview", new Interview());
        return "interviewRegistration";
    }

    @PostMapping("/intRegister")
    public String processInterviewReg(@ModelAttribute("interview") Interview interview, Model model) {

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

    @GetMapping("/intQuest")
    public String Interview(Model model) {
        model.addAttribute("interview", new Interview());
        return "interviewTemp";
    }

    @PostMapping("/intQuest")
    public String processInterviewQuestions(@ModelAttribute("interview") Interview interview, Model model) {
        interviewRepository.save(interview);
        model.addAttribute("message", "Interview sent");
        File f = new File("program.txt");
        model.addAttribute("interview", f);

        String q1= interview.getBehQuest1();
        String a1= interview.getAnswer1();

        String q2= interview.getBehQuest2();
        String a2= interview.getAnswer2();

        String q3= interview.getBehQuest3();
        String a3= interview.getAnswer3();

        String q4= interview.getJobQuest1();
        String a4= interview.getAnswer4();

        String q5= interview.getJobQuest2();
        String a5= interview.getAnswer5();

        String q6= interview.getJobQuest3();
        String a6= interview.getAnswer6();

        String content = q1 +a1 +q2 +a2 + q3 +a3 +q4 +a4 +q5 +a5 +q6 +a6;

        try {



            File file = new ClassPathResource("countries.txt").getFile();


        if (!f.exists()) {
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(f.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();



        return "redirect:/";
    }
}


