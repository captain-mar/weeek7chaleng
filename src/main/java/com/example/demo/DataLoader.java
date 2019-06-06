package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;






  //delete this before merge
  @Autowired
 JobRepo jobRepo;

  @Override
  public void run(String... strings) throws Exception{
    roleRepository.save(new Role("USER"));
    roleRepository.save(new Role("ADMIN"));

    Role adminRole = roleRepository.findByRole("ADMIN");
    Role userRole = roleRepository.findByRole("USER");

    User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true,
            "jim");
    user.setRoles(Arrays.asList(userRole));
    userRepository.save(user);

    user = new User("admin@admin.com", "password",
            "Admin",
            "User", true,
            "admin");
    user.setRoles(Arrays.asList(adminRole));
    userRepository.save(user);

//      Job myjob = new Job();
//      myjob.setPositionTitle("test");
//      myjob.setKeyWord("Java, Python, SQL");
//
//
//      ArrayList<String> resume = new ArrayList<String>();
//      resume.add("Java");
//      resume.add("agile");
//      resume.add("teamwork");
//
//      User testUser = new User();
//      testUser.setUsername("TestUser");
//      testUser.setResult(resume);
//      userRepository.save(testUser);
//      jobRepo.save(myjob)

  }
}
