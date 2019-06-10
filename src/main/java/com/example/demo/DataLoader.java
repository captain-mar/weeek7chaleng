//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Set;
//
//@Component
//public class DataLoader implements CommandLineRunner {



//    Resume resume;


//
//    //delete this before merge
//@Autowired
//    UserRepository userRepository;
//
//@Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    JobRepo jobRepo;
//    @Autowired
//    ResumeRepository resumeRepository;
//    @Override
//    public void run(String... strings) throws Exception{
//        roleRepository.save(new Role("USER"));
//        roleRepository.save(new Role("ADMIN"));
//
//        Role adminRole = roleRepository.findByRole("ADMIN");
//        Role userRole = roleRepository.findByRole("USER");
//        // Resume resume = resumeRepository.findAl
//
//        Collection<Resume> resume = new ArrayList<>();
//        ArrayList<String> res= new ArrayList<>();
//        res.add("java");
//        res.add("sql");
//        res.add("python");
//        res.add("some");
//
//        Resume resume1 =new Resume("file1","//c:file",res);
//        System.out.println(resume1.getResult());
//
//
//
//        //resume.add("Java");
//        // resume.add("agile");
//        ///resume.add("teamwork");
//        Set<Resume> resumes ;
//
//
//        User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true,
//                "jim");
//        user.setRoles(Arrays.asList(userRole));
//
//        // setResumes(resume1);
//
//       // resumes.add(resume1);
//        // user.setResumes(resume);
//        // resume.setResult(resume);
//        userRepository.save(user);
//
//        user = new User("admin@admin.com", "password",
//                "Admin",
//                "User", true,
//                "admin");
//       // user.setResumes(resumes);
//        user.setRoles(Arrays.asList(adminRole));
//        userRepository.save(user);
//
//
//
//
//        Job myjob = new Job();
//        myjob.setCompanyName("TWITTER");
//        myjob.setPositionTitle("Java Dev");
//        myjob.setKeyWord("Java, Python, SQL");
//        myjob.setTypeOfJob("Full Time");
//        myjob.setSalary(100000.00);
//        myjob.setDescription("This is a demo job");
//        jobRepo.save(myjob);
//
//        user.setJobs(Arrays.asList(myjob));
//
//        Job myjob1 = new Job();
//        myjob1.setCompanyName("TWITTER");
//        myjob1.setPositionTitle("Python Dev");
//        myjob1.setKeyWord("Java, Python, SQL");
//        myjob1.setTypeOfJob("Full Time");
//        myjob1.setSalary(100000.00);
//        myjob1.setDescription("This is a demo job");
//        jobRepo.save(myjob1);
//
//        user.setJobs(Arrays.asList(myjob1));
//
//        Job myjob2 = new Job();
//        myjob2.setCompanyName("TWITTER");
//        myjob2.setPositionTitle("SQL Administrator");
//        myjob2.setKeyWord("Java, Python, SQL");
//        myjob2.setTypeOfJob("Full Time");
//        myjob2.setSalary(100000.00);
//        myjob2.setDescription("This is a demo job");
//        jobRepo.save(myjob2);
//
//        user.setJobs(Arrays.asList(myjob2));
//
//        Job myjob3 = new Job();
//        myjob3.setCompanyName("TWITTER");
//        myjob3.setPositionTitle("Business Analyst");
//        myjob3.setKeyWord("Java, Python, SQL");
//        myjob3.setTypeOfJob("Full Time");
//        myjob3.setSalary(100000.00);
//        myjob3.setDescription("This is a demo job");
//        jobRepo.save(myjob2);
//
//        user.setJobs(Arrays.asList(myjob3));
//
//
//    }
//}