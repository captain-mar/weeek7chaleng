
package com.example.demo;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

    @Controller
    public class AntController {

        @Autowired
        JobRepo jobRepo;

        @Autowired
        UserService userService;

        @Autowired
        RoleRepository roleRepository;

        @Autowired
        UserRepository userRepository;

        @Autowired
        InterviewRepository interviewRepository;






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
////        Role userRole = roleRepository.findByRole("USER");
//
//        User admin2 = new User();
//        admin2.setEnabled(true);
//        admin2.setFirstName("Anthony");
//        admin2.setLastName("Valle");
//        admin2.setRoles(Arrays.asList(adminRole));
//        admin2.setEmail("valleganthony@gmail.com");
//        admin2.setUsername("valleant");
//        admin2.setPassword("password");

        //    }
//
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
//        String var1 = job.getKeyWord();
//        jobMethods.SplitKeyWords(var1);
            job.setAdminCreatorId(userService.getUser().getId());
            jobRepo.save(job);
            return "redirect:/";
        }

        @RequestMapping("admin/detail/job/{id}")
        public String adminViewJob(@PathVariable("id") long id, Model model) {
            model.addAttribute("job", jobRepo.findById(id).get());
            if (userService.getUser() != null) {
                model.addAttribute("user_id", userService.getUser().getId());

            }
            return "show2";
        }






        @RequestMapping("/detail/job/{id}")
        public String applyToJob(@PathVariable("id") long id, Model model) {
            model.addAttribute("job", jobRepo.findById(id).get());
            if (userService.getUser() != null) {
                model.addAttribute("user_id", userService.getUser().getId());

            }
            //This is the Start of the method implementation.
            JobMethods jobMethods = new JobMethods();
            //User user = new User(); // making empty objects
            Job job = new Job();// making empty objects
            User  user = userService.getUser();// filling the objects to vars
            job = jobRepo.findById(id).get();// finding object in DB and filling in var
            System.out.println(user.getFirstName());//This should print the user's name
            System.out.println(job.getPositionTitle()); //this should print the job name
            boolean match = jobMethods.compareTool(user,job); //runs the compare method to make sure user meets 80% threshold.
            System.out.println(match);// will be true if above 80%
            model.addAttribute("match",match);//adds the boolean to a model to be used on the web page


            //New section to make the interview
            boolean interviewBool = false;
            if (match==true) {
                interviewBool =true;
                long updateID = userService.getUser().getId();
                user = userRepository.findById(updateID).get();
                System.out.println(user.getFirstName());
                System.out.println(user.getUsername());
                user.setJobs(Arrays.asList(job)); // a user now has a job.

                Interview interview = new Interview();
                interview.setJob(job);
                System.out.println(interview.getId());
                System.out.println("this is an interview for " + interview.getJob().getPositionTitle());

                model.addAttribute("interview",interviewBool); // this is the boolean value that will let us know whether
                // -> the form should be displayed for an interview.

                // Sub section for the interview class. Will be sending the three technical questions to the interview class
                interview.setJobQuest1(job.getQuestionOne());
                interview.setJobQuest2(job.getQuestionTwo());
                interview.setJobQuest3(job.getQuestionThree());
                userRepository.save(user);
                interviewRepository.save(interview);

            }
            return "show2";

        }

        @RequestMapping("/update/job/{id}")
        public String updateMessage(@PathVariable("id") long id, Model model){
            model.addAttribute("job", jobRepo.findById(id).get());
            return "jobform";
        }

        @RequestMapping("/delete/job/{id}")
        public String delMessage(@PathVariable("id") long id) {
            jobRepo.deleteById(id);
            return "redirect:/";
        }


        @RequestMapping("/mypost")// <-- Call back all posts made by user.
        public String profilePage(Model model){
            long creatorID= userService.getUser().getId();
            ArrayList<Job> results =(ArrayList<Job>) jobRepo.findByAdminCreatorId(creatorID);


            model.addAttribute("user", userService.getUser().getId());
            model.addAttribute("results",results);




            return "mypost";
        }



//
//    @PostMapping("/addjob")
//    public String processForm(@Valid Job job,
////                              long id,
//                              Model model){
//
//        long id = userService.getUser().getId();
//        model.addAttribute("userId",userService.getUser().getId());
//
//        User user = userRepository.findById(id);
////        User user = userService.getUser();// filling the objects to vars
//
//
//       // Job job = new Job();// making empty objects
//        job = jobRepo.findById(id).get();
//
//         myJobs=;
//        myJobs.add(job);
//        user.setJobs(myJobs);
//
//        return "redirect:/";
//    }




    }

