package com.personalprojects.modeler.Reposiory;

import com.personalprojects.modeler.Model.Flow;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FlowRepository extends MongoRepository<Flow, ObjectId> {

    Optional<Flow> findFlowByName(String flowName);


}
