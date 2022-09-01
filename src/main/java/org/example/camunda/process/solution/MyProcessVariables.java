package org.example.camunda.process.solution;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class MyProcessVariables extends ProcessVariables {

  private String businessKey;
  private Boolean result;
  private String status;
  private String subject;
  private String email;
  private String message;
  private String caseId;
  private String searchTerm;
}
