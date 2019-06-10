package com.example.demo;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import java.util.*;

import static java.sql.DriverManager.println;

@Controller
public class HomeController {
    User user;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JobRepo jobRepo;
    @Autowired
    ResumeRepository resumeRepository;



    @Autowired HomeControllerOona homeControllerOona;

    @Autowired
    private JavaMailSender sender;
    Path fi; // to check for sent email with
    String fname;
    String email;

    private Set<Resume> resumes;

    @Autowired
    UserService userService;
    ArrayList<String> arrayList = new ArrayList<>();


    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "registration";
        }


        else {
              user.setResumes(resumes);
            userService.saveUser(user);
            email = user.getEmail();
           /// model.addAttribute("user_id",userService.getUser().getId());
            model.addAttribute("message", "User Account Created");
        }
        if(email==user.getEmail()){
            try {
                sendEmail();
            } catch (Exception ex) {
                // return "Error in sending email: " + ex;
                return "/email";
            }
        }
        return "redirect:/addre";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }



    @RequestMapping("/")

    public String listJobs(Model model) {
        model.addAttribute("jobs", jobRepo.findAll());
        model.addAttribute("resumes",resumeRepository.findAll());


        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
       // homes();
        return "list";
    }

    @GetMapping("/add")
    public String jobForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Job job, BindingResult result){

        if(result.hasErrors()){
            return "jobform";
        }
        job.setUser(userService.getUser());
        jobRepo.save(job);
        return "redirect:/";
    }
    @GetMapping("/addre")
    public String resumeform(Model model) {
        model.addAttribute("resume", new Resume());
        return "resumeform";
    }

    @PostMapping("/processre")
    public String processForm(@Valid Resume resume, BindingResult result,  @RequestParam("file") MultipartFile file, Model model){

        if(result.hasErrors()){
            return "resumeform";
        }
        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(file.getOriginalFilename());
            fi = path;
            Files.write(path, bytes);
            String filename = file.getOriginalFilename();

            resume.setFilename(filename);
            fname = filename;
            try (Scanner s = new Scanner(new File(filename)).useDelimiter(" ")) {
                // \\s* in regular expressions means "any number or whitespaces".
                // We could've said simply useDelimiter("-") and Scanner would have
                // included the whitespaces as part of the data it extracted.
                while (s.hasNext()) {
                    arrayList.add(s.next());
                }
            } catch (FileNotFoundException e) {
                // Handle the potential exception
            }


        } catch (IOException e) {

            e.printStackTrace();

            return "redirect:/addre";

        }
        resume.setResult(arrayList);
       // model.addAttribute("user_id", userService.getUser().getId());
        resume.setUser(userService.getUser());
       // resumes.add(resume);
        resumeRepository.save(resume);
        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("resume", resumeRepository.findById(id).get());

        return "resumeform";
    }


    public String getCurrentTime() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);

    }

  //  @RequestMapping("/send")
    @ResponseBody
    String home() {
        try {
            sendEmail();
            return "Email Sent!";
        } catch (Exception ex) {
            return "Error in sending email: " + ex;
        }
    }

    private void sendEmail() throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        //helper.setTo("fikru07@gmail.com");
        helper.setText("Well Come to job finder.com ");
        helper.setSubject("registration confirm");

        sender.send(message);
    }
    @RequestMapping("/email")
    public String emailNotFound(){
return "email";
    }

  //  @RequestMapping("/send2")
    @ResponseBody
    String homes() {
        try {
            sendEmails();
            return "Email Sent!";
        } catch (Exception ex) {
            return "/email";
        }
    }
  //  String intervewfileName=homeControllerOona.fname;

    private void sendEmails() throws Exception {
        MimeMessage message = sender.createMimeMessage();

        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setText("");
        helper.setSubject("interview and answers document");

        //ClassPathResource file = new ClassPathResource(fname);
        //helper.addAttachment(fname,file);
        FileSystemResource file = new FileSystemResource(fname);
        helper.addAttachment(file.getFilename(), file);

        sender.send(message);
    }
    @PostConstruct
    public void fillTable(){
        System.out.println("loading data...");
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");
        // Resume resume = resumeRepository.findAl

        Collection<Resume> resume = new ArrayList<>();
        ArrayList<String> res= new ArrayList<>();
        res.add("java");
        res.add("sql");
        res.add("python");
        res.add("some");

        Resume resume1 =new Resume("file1","//c:file",res);
        System.out.println(resume1.getResult());



        //resume.add("Java");
        // resume.add("agile");
        ///resume.add("teamwork");
        Set<Resume> resumes ;


        User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true,
                "jim");
        user.setRoles(Arrays.asList(userRole));

        // setResumes(resume1);

        // resumes.add(resume1);
        // user.setResumes(resume);
        // resume.setResult(resume);
        userRepository.save(user);

        user = new User("admin@admin.com", "password",
                "Admin",
                "User", true,
                "admin");
        // user.setResumes(resumes);
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);




        Job myjob = new Job();
        myjob.setCompanyName("TWITTER");
        myjob.setPositionTitle("Java Dev");
        myjob.setKeyWord("Java, Python, SQL");
        myjob.setTypeOfJob("Full Time");
        myjob.setSalary(100000.00);
        myjob.setDescription("This is a demo job");
        jobRepo.save(myjob);

        user.setJobs(Arrays.asList(myjob));

        Job myjob1 = new Job();
        myjob1.setCompanyName("TWITTER");
        myjob1.setPositionTitle("Python Dev");
        myjob1.setKeyWord("Java, Python, SQL");
        myjob1.setTypeOfJob("Full Time");
        myjob1.setSalary(100000.00);
        myjob1.setDescription("This is a demo job");
        jobRepo.save(myjob1);

        user.setJobs(Arrays.asList(myjob1));

        Job myjob2 = new Job();
        myjob2.setCompanyName("TWITTER");
        myjob2.setPositionTitle("SQL Administrator");
        myjob2.setKeyWord("Java, Python, SQL");
        myjob2.setTypeOfJob("Full Time");
        myjob2.setSalary(100000.00);
        myjob2.setDescription("This is a demo job");
        jobRepo.save(myjob2);

        user.setJobs(Arrays.asList(myjob2));

        Job myjob3 = new Job();
        myjob3.setCompanyName("TWITTER");
        myjob3.setPositionTitle("Business Analyst");
        myjob3.setKeyWord("Java, Python, SQL");
        myjob3.setTypeOfJob("Full Time");
        myjob3.setSalary(100000.00);
        myjob3.setDescription("This is a demo job");
        jobRepo.save(myjob2);

        user.setJobs(Arrays.asList(myjob3));
    }

}




























































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































