package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class AntController {

    @Autowired
    JobRepo jobRepo;

    @Autowired
    UserService userService;

//
//    @PostConstruct
//    public void filltables(){
//        Role admin = new Role();
//        admin.setRole("ADMIN");
//
//        Role user = new Role();
//        user.setRole("USER");
//
//        roleRepository.save(admin);
//        roleRepository.save(user);
//
//        Role adminRole = roleRepository.findByRole("ADMIN");
//        Role userRole = roleRepository.findByRole("USER");
//
//        User admin2 = new User();
//        admin2.setEnabled(true);
//        admin2.setFirstName("Anthony");
//        admin2.setLastName("Valle");
//        admin2.setRoles(Arrays.asList(adminRole));
//        admin2.setEmail("valleganthony@gmail.com");
//        admin2.setUsername("valleant");
//        admin2.setPassword("password");
//
//    }

    @GetMapping("/addjob")
    public String messageForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/processjob")
    public String processForm(@Valid Job job, BindingResult result){

        if(result.hasErrors()){
            return "jobform";
        }

        job.setAdminCreatorId(userService.getUser().getId());
        jobRepo.save(job);
        return "redirect:/";
    }







}
