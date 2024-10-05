package com.personalprojects.modeler.Model;


import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("flow_definition")
@Builder
public class Flow {


    @Id
    ObjectId id;
    @Indexed(unique = true)
    String name;
    String startTaskKey;
    String type;
    List<String> taskDefinitionKeys = new ArrayList<>();
    String endTaskKey;
}
