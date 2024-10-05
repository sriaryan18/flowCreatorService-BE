package com.personalprojects.modeler.Controller;


import com.personalprojects.modeler.Model.Flow;
import com.personalprojects.modeler.Model.TaskDefinition;
import com.personalprojects.modeler.Service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/flow")
@RestController
public class FlowController {

    @Autowired
    FlowService flowService;

    @PostMapping("/createNew")
    public ResponseEntity<?> createNewFlow(
            @RequestBody Flow flowObject){
        try{

        Flow flow =  flowService.createNewFlow(flowObject);
        return  new ResponseEntity<Flow>(flow, HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("{flowName}")
    public ResponseEntity<Flow> getFlow(@PathVariable String flowName){
        try{

        Flow flow = flowService.getFlow(flowName);
        return new ResponseEntity<>(flow,HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllTask(){
        List<String> allFlows = flowService.getNamesOfAllFlows();
        return new ResponseEntity<>(allFlows,HttpStatus.OK);
    }

}
