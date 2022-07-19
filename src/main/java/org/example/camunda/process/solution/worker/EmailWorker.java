package org.example.camunda.process.solution.worker;

import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.example.camunda.process.solution.MyProcessVariables;
import org.example.camunda.process.solution.service.email.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("email")
public class EmailWorker {

  private static final Logger LOG = LoggerFactory.getLogger(EmailWorker.class);

  private EmailServiceImpl emailService;

  public EmailWorker() {}

  @Autowired
  public EmailWorker(EmailServiceImpl emailService) {
    this.emailService = emailService;
  }

  @ZeebeWorker(type = "email", autoComplete = true)
  public MyProcessVariables send(@ZeebeVariablesAsType MyProcessVariables variables) {

    LOG.info("Invoking myService with variables: " + variables);

    final String emailSubject = variables.getSubject();
    final String emailBody = variables.getMessage();
    final String emailTo = variables.getEmail();

    LOG.info("Sending email with message content: {}", emailBody);

    emailService.sendSimpleMessage(emailTo, emailSubject, emailBody);

    variables.setStatus("success");

    return (MyProcessVariables)
        new MyProcessVariables().setResult(true); // new object to avoid sending unchanged variables
  }
}
