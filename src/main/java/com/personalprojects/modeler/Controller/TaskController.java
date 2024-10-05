package com.personalprojects.modeler.Controller;


import com.personalprojects.modeler.Model.Flow;
import com.personalprojects.modeler.Model.TaskDefinition;
import com.personalprojects.modeler.Service.FlowService;
import com.personalprojects.modeler.Service.TaskDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/task")
@RestController
public class TaskController {

    @Autowired
    FlowService flowService;

    @Autowired
    TaskDefinitionService taskDefinitionService;

    @PostMapping("/add/{flowName}")
    public ResponseEntity<?> addTask(@RequestBody TaskDefinition taskDefinition,
                                     @PathVariable String flowName){
        try{
            Flow flow = flowService.addTask(flowName,taskDefinition);
            return new ResponseEntity<>(flow, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{taskName}")
    public ResponseEntity<TaskDefinition> getTaskDefinition(@PathVariable String taskName){
        TaskDefinition taskDefinition =  taskDefinitionService.getTaskDefinition(taskName);
        return new ResponseEntity<>(taskDefinition, HttpStatus.OK);
    }

}
