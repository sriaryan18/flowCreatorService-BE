package com.personalprojects.modeler.Reposiory;

import com.personalprojects.modeler.Model.TaskDefinition;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TaskDefinitionRepository extends MongoRepository<TaskDefinition, ObjectId> {

    Optional<TaskDefinition> findByName(String taskName);
}
