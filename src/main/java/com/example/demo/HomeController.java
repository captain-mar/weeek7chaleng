package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class HomeController {

  @Autowired
  JobRepo jobRepo;

  @Autowired
  UserService userService;
    ArrayList<String> arrayList = new ArrayList<>();

  @GetMapping("/register")
  public String showRegistrationPage(Model model){
    model.addAttribute("user", new User());
    return "registration";
  }

  @PostMapping("/register")
  public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,@RequestParam("file") MultipartFile file){

    if(result.hasErrors()){
      return "registration";
    }

    else {
        if (file.isEmpty()) {
            return "redirect:/register";
        }
        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(file.getOriginalFilename());
            Files.write(path, bytes);
            String filename = file.getOriginalFilename();

            user.setFilename(filename);

            File fff = new File(filename);

            InputStream ff = new FileInputStream(fff);



            try (Scanner s = new Scanner(new File(filename)).useDelimiter(" ")) {
                // \\s* in regular expressions means "any number or whitespaces".
                // We could've said simply useDelimiter("-") and Scanner would have
                // included the whitespaces as part of the data it extracted.
                while (s.hasNext()) {
                    arrayList.add(s.next());
                }
            }
            catch (FileNotFoundException e) {
                // Handle the potential exception
            }


        }catch (IOException e){

            e.printStackTrace();

            return "redirect:/register";

        }
        user.setResult(arrayList);
        userService.saveUser(user);
        model.addAttribute("message", "User Account Created");
    }
    return "redirect:/";
  }

  @RequestMapping("/login")
  public String login(){
    return "login";
  }

  @RequestMapping("/")
  public String listCourses(Model model){
    model.addAttribute("jobs", jobRepo.findAll());
    if(userService.getUser() != null) {
      model.addAttribute("user_id", userService.getUser().getId());
    }
    return "list";
  }




  public String getCurrentTime(){

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    LocalDateTime now = LocalDateTime.now();

    return dtf.format(now);

  }



}
