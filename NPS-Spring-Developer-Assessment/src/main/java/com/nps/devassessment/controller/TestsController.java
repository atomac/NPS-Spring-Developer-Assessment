package com.nps.devassessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.BeanUtils;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.repo.WorkflowRepo;
import com.nps.devassessment.service.WorkflowRepoService;
import com.nps.devassessment.service.impl.WorkflowRepoServiceImpl;

/**
 * Copyright 2021
 * 
 * File Name:   TestsController.java
 * 
 * Purpose  : Class to define the workflow entity tests controller 
 *            functionality. Each CRUD action is demonstrated.
 *            
 * Version
 *    1.0   16 Jun 2021 - initial release.
 * 
 * @author mccormam
 * 
 */
@RestController
@RequestMapping("/api/v1/tests-controller")
public class TestsController
{
    @Autowired
    private WorkflowRepo workflowRepo;
    
    @Autowired
    private WorkflowRepoService workflowRepoService;


    /**
     * Perform ad hoc work entity list query.
     * 
     * @param name - defined query.
     * @return the list of work flow entity.
     */
    @GetMapping("/workflowentity/query/{query}")
    public ResponseEntity<List<WorkflowEntity>> findWorkFlowEntity(
                    @RequestParam(required = false) String name)
    {
        return ResponseEntity.ok(workflowRepo.executeQuery(name));
    }

    
    /**
     * Method to retrieve work flow entity record by id.
     * 
     * @param workflowId - work flow entity id.
     * @return work flow record returned.
     */
    @GetMapping("/workflowentity/findById/{workflowentityid}")
    public ResponseEntity<WorkflowEntity> findWorkflowEntity(@PathVariable Long workflowId)
    {
        return ResponseEntity.ok(workflowRepoService.findWorkflowById(workflowId));
    }

    /**
     * Method to retrieve all work flow entity record.
     * 
     * @return all work flow records.
     */
    @GetMapping("/workflowentity/findAll")
    public ResponseEntity<List<WorkflowEntity>> findAllWorkflowEntity()
    {        
        return ResponseEntity.ok(workflowRepoService.findAll());
    }
    
    /**
     * Method to page a collection of work flow entity record.
     * 
     * @param pageNo - page number of records to be retrieved.
     * @return page or record to be retrieved.
     */
    @GetMapping("/workflowentity/findByPage/{pageNo}")
    public ResponseEntity<List<WorkflowEntity>> findWorkflowEntityByPage(@PathVariable Integer pageNo)
    {
        Integer pageSize = 50;
        
        String sortBy = "workflowId";
        
        
        return ResponseEntity.ok(workflowRepoService.findAll(pageNo, pageSize, sortBy));
    }
    

    /**
     * Method to create the work flow entity record.
     * 
     * @param workflowEntity - work flow entity to be created.
     * @return work flow entity create response.
     */
    @PostMapping("/workflowentity/create")
    public ResponseEntity<WorkflowEntity> createWorkflowEntity(
                    @RequestBody WorkflowEntity workflowEntity)
    {
        return ResponseEntity.ok(workflowRepo.save(workflowEntity));
    }

    /**
     * Method to update the work flow entity record by id.
     * 
     * @param workflowEntityId - work flow entity id to be updated.
     */
    @SuppressWarnings("unchecked")
    @PatchMapping("/workflowentity/update/{workflowentityid}")
    public ResponseEntity<? extends WorkflowEntity> updateWorkflowById(
                    @PathVariable("workflowentityid") long workflowentityId,
                    @RequestBody WorkflowEntity workflowEntity)
    {
        Boolean bExist = workflowRepo.existsById(workflowentityId);
        
        // If the record exists, update.
        if (bExist)
        {
            return ((HeadersBuilder<BodyBuilder>) ResponseEntity.ok(workflowRepo.save(workflowEntity))).build();
        }

        // Exists criteria fails, return null.
        return null;
    }
    
    /**
     * Method to update collection the work flow entity record.
     * 
     * @param workflowEntities - collection of work flow entities to be updated.
     */
    @SuppressWarnings("unchecked")
    @PatchMapping("/workflowentity/updateAll/{workflowentities}")
    public ResponseEntity<? extends WorkflowEntity> updateWorkflowEntities(
                    @RequestBody  Iterable<? extends WorkflowEntity> workflowEntities)
    {
            return ((HeadersBuilder<BodyBuilder>) ResponseEntity.ok(workflowRepo.saveAll(workflowEntities))).build();
    }

    
    /**
     * Method to delete the work flow entity record by id
     * 
     * @param workflowEntityId - work flow entity id to be deleted.
     */
    @DeleteMapping("/workflowentity/delete/{workflowentityid}")
    public ResponseEntity<Void> deleteWorkflowById(@PathVariable Long workflowEntityId)
    {
        workflowRepo.deleteById(workflowEntityId);
        
        return ResponseEntity.ok().build();
    }
    
    
    /**
     * Method to delete the work flow entity record.
     * 
     * @param workflowEntity - work flow entity to be deleted
     */
    @DeleteMapping("/workflowentity/deleteEntities/{workflowentity}")
    public ResponseEntity<Void> deleteWorkflowEntity(@PathVariable WorkflowEntity workflowEntity)
    {
        workflowRepo.delete(workflowEntity);
        
        return ResponseEntity.ok().build();
    }
    
    
    /**
     * Method to delete collection of work flow entity records.
     * 
     * @param workflowEntities - collection of work flow entity to be deleted
     */    
    @DeleteMapping("/workflowentity/deleteEntities/{workflowentities}")
    public ResponseEntity<Void> deleteWorkflowEntities(@PathVariable Iterable<? extends WorkflowEntity> workflowEntities)
    {
        workflowRepo.deleteAll(workflowEntities);
        
        return ResponseEntity.ok().build();
    }
    
    
    /**
     * Method to delete all of the work flow entity records.
     * 
     */
    @DeleteMapping("/workflowentity/deleteAll")
    public ResponseEntity<Void> deleteWorkflowAll()
    {
        workflowRepo.deleteAll();
        
        return ResponseEntity.ok().build();
    }

}
