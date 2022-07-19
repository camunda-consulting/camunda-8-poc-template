package org.example.camunda.process.solution;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyProcessVariables extends ProcessVariables {

  private String status;
  private String subject;
  private String email;
  private String message;
  private String caseId;

}
