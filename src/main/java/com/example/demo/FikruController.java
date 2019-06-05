package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
@Controller

public class FikruController {
    private static String UPLOADED_FOLDER = "F://";

    @Autowired UserRepository userRepository;
    ArrayList<String> arrayList = new ArrayList<>();

    @RequestMapping("/f")

    public String listcar(Model model){

        model.addAttribute("users",userRepository.findAll());
        return "listf";
    }

    @GetMapping("/addf")

    public String carForm(Model model){
        model.addAttribute("user",new User());
        return "userform";
    }

    @PostMapping("/processf")

    public String processActor(@ModelAttribute User user, @RequestParam("file") MultipartFile file) {


        if (file.isEmpty()) {
            return "redirect:/addf";
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

            return "redirect:/addf";

        }
        user.setResult(arrayList);
        userRepository.save(user);
        return "redirect:/f";
    }
}
