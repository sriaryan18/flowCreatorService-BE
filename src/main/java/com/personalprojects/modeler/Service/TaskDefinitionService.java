package com.personalprojects.modeler.Service;

import com.personalprojects.modeler.Model.Flow;
import com.personalprojects.modeler.Model.TaskDefinition;
import com.personalprojects.modeler.Reposiory.TaskDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskDefinitionService {

    @Autowired
    TaskDefinitionRepository taskDefinitionRepository;

    public TaskDefinition saveNewTask(Flow flow, TaskDefinition taskDefinition){
        taskDefinition.setFlow(flow);
        taskDefinitionRepository.save(taskDefinition);
        return taskDefinition;
    }

    public TaskDefinition getTaskDefinition(String taskDefName){
        Optional<TaskDefinition> taskDefinitionOptional =  taskDefinitionRepository.findByName(taskDefName);
        if(taskDefinitionOptional.isPresent()){
            return taskDefinitionOptional.get();
        }else{
            throw new RuntimeException("Task not found with the given name");
        }
    }

}
