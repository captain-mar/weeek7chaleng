//package com.example.demo;
//
//import javax.persistence.*;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
//@Entity
//public class Message {
////  @Id
////  @GeneratedValue(strategy = GenerationType.AUTO)
////  private long id;
//
////  @NotNull
////  @Size(min = 4)
////  private String title;
////  @NotNull
////  @Size(min = 3)
////  private String content;
//
////  private String postDate;
////
//  @ManyToOne (fetch = FetchType.EAGER)
//  @JoinColumn(name = "user_id")
//  private User user;
//
//
//
//  public Message() {
//  }
//
//
//  public Message(@NotNull @Size(min = 4) String title, @NotNull @Size(min = 3) String content, @NotNull String postDate, @NotNull @Size(min = 3) String postedBy) {
//    this.title = title;
//    this.content = content;
//    this.postDate = postDate;
//
//  }
//
//  public long getId() {
//    return id;
//  }
//
//  public void setId(long id) {
//    this.id = id;
//  }
//
//  public String getTitle() {
//    return title;
//  }
//
//  public void setTitle(String title) {
//    this.title = title;
//  }
//
//  public String getContent() {
//    return content;
//  }
//
//  public void setContent(String content) {
//    this.content = content;
//  }
//
//  public String getPostDate() {
//    return postDate;
//  }
//
//  public void setPostDate(String postDate) {
//    this.postDate = postDate;
//  }
//
//  public User getUser() {
//    return user;
//  }
//
//  public void setUser(User user) {
//    this.user = user;
//  }
//}
