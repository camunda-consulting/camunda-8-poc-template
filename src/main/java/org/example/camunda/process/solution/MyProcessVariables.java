package org.example.camunda.process.solution;

public class MyProcessVariables extends ProcessVariables {

  private String status;
  private String subject;
  private String email;
  private String message;
  private String getSubject;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String mesasge) {
    this.message = mesasge;
  }

  public String getGetSubject() {
    return getSubject;
  }

  public void setGetSubject(String getSubject) {
    this.getSubject = getSubject;
  }
}
