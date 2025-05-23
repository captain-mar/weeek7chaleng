package com.example.demo;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="User_Data")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull
  private long id;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "username")
  private String username;



  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Collection<Role> roles;


 // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
 // private Collection<Resume> resumes;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "job_id"))
  private Collection<Job> jobs;

    public Set<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(Set<Resume> resumes) {
        this.resumes = resumes;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Resume> resumes;


    //public Collection<Resume> getResumes() {
  //  return resumes;
 // }

  //public void setResumes(Collection<Resume> resumes) {
  //  this.resumes = resumes;
  //}

  public User() {
  }

public Collection<Job> getJobs(){
    return jobs;
}

public void setJobs( Collection<Job> jobs){
    this.jobs=jobs;
}

  public User(String email, String password, String firstName, String lastName, boolean enabled, String username) {
    this.email = email;
    this.setPassword(password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.enabled = enabled;
    this.username = username;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    this.password = passwordEncoder.encode(password);
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {
    this.roles = roles;
  }
}
