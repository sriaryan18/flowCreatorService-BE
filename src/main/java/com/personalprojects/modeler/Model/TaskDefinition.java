package com.personalprojects.modeler.Model;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("task_definition")
public class TaskDefinition {

    @Id
    ObjectId id;

    String flowName;
    String name;
    String taskType;
    String assignee;
    JsonNode form;
    JsonNode automaticTaskConfig;
    JsonNode decisionMeta;

    @DBRef
    Flow flow;

}
