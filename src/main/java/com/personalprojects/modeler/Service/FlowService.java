package com.personalprojects.modeler.Service;

import com.personalprojects.modeler.Model.Flow;
import com.personalprojects.modeler.Model.TaskDefinition;
import com.personalprojects.modeler.Reposiory.FlowRepository;
import com.personalprojects.modeler.Reposiory.TaskDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlowService {

    @Autowired
    FlowRepository flowRepository;

    @Autowired
    TaskDefinitionService taskDefinitionService;

    public Flow createNewFlow(Flow flow) throws  RuntimeException{
        try{
       String flowName = flow.getName();
        flowRepository.findFlowByName(flowName)
                .ifPresentOrElse((value)  -> {
                    throw new RuntimeException("Already a flow by this name");
                },() -> {
                    flowRepository.insert(flow);
                });
        return flow;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Flow addTask(String flowName , TaskDefinition taskDefinition){
        try{
           return  flowRepository.findFlowByName(flowName)
                    .map((flow) -> {
                        List<String> allTask = flow.getTaskDefinitionKeys();
                        if(allTask == null){
                            allTask = new ArrayList<>();
                        }
                        for(String taskName : allTask){
                            if(taskName.equalsIgnoreCase(taskDefinition.getName())) {
                                throw new RuntimeException("Task definition with this key is already present");
                            }
                        }
                        allTask.add(taskDefinition.getName());
                        flow.setTaskDefinitionKeys(allTask);
                        taskDefinitionService.saveNewTask(flow,taskDefinition);
                        flowRepository.save(flow);

                        return flow;
                    }).orElseThrow(() -> new RuntimeException("Flow not found with name: " + flowName));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public Flow getFlow(String flowName){
        Optional<Flow> flowOptional = flowRepository.findFlowByName(flowName);
        if(flowOptional.isPresent()){
            return flowOptional.get();
        }else{
            throw new RuntimeException("Flow not found");
        }
    }

    public List<String> getNamesOfAllFlows(){
        return flowRepository.findAll().stream()
                .map((flow) -> flow.getName())
                .collect(Collectors.toList());
    }


}
