package org.example.camunda.process.solution.service.tasklist;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.*;
import io.camunda.tasklist.dto.Form;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskState;
import io.camunda.tasklist.exception.TaskListException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TaskListServiceImpl implements TaskListService {

    @Value("${tasklist.client.uri}")
    String uri;

    @Value("${tasklist.client.auth.type}")
    String authType;

    @Value("${tasklist.client.user}")
    String user;

    @Value("${tasklist.client.secret}")
    String clientSecret;

    CamundaTaskListClient client;

    private CamundaTaskListClient getClient() throws TaskListException {
        if(authType.equalsIgnoreCase("saas")) {
            return getSaasClient();
        } else {
            return getSimpleClient();
        }
    }


    private CamundaTaskListClient getSaasClient() throws TaskListException {
        if(client == null) {
            SaasAuthentication sa = new SaasAuthentication(user, clientSecret);
            client = new CamundaTaskListClient.Builder().authentication(sa)
                    .taskListUrl(uri).build();
        }
        return client;
    }

    private CamundaTaskListClient getSimpleClient() throws TaskListException {
        if(client == null) {
            SimpleAuthentication sa = new SimpleAuthentication("demo", "demo");
            client = new CamundaTaskListClient.Builder().shouldReturnVariables().authentication(sa)
                    .taskListUrl(uri).build();
        }
        return client;
    }


    public List<Task> getAssigneeTasks(String userId) throws TaskListException {
        return getClient().getAssigneeTasks(userId, TaskState.CREATED, 50, true);
    }

    public Task getTask(String taskId) throws TaskListException {
        return getClient().getTask(taskId);
    }

    public Form getFormById(String formId, String processDefinitionId) throws TaskListException {
        return getClient().getForm(formId, processDefinitionId);
    }

    public Form getFormByKey(String formKey, String processDefinitionId) throws TaskListException {
        String formId = parseFormIdFromKey(formKey);
        return getFormById(formId, processDefinitionId);
    }

    static String parseFormIdFromKey(String formKey) {
        Pattern pattern = Pattern.compile("[^:]+:[^:]+:(.*)");
        Matcher matcher = pattern.matcher(formKey);
        while(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }


}
