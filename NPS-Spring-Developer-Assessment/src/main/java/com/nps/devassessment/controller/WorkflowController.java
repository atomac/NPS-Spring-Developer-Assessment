package com.nps.devassessment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.service.WorkflowRepoService;

/**
 * Copyright 2021
 * 
 * File Name:   WorkflowController.java
 * 
 * Purpose  : Class that defines the functionality of the work flow controller.
 *            
 * Version
 *    1.0   16 Jun 2021 - initial release.
 * 
 * @author mccormam
 * 
 */
@RestController
@RequestMapping("/workflowentity")
public class WorkflowController 
{
    @Autowired
    WorkflowRepoService workflowRepoService;
 
    /**
     * Method to page a collection of work flow entity record.
     * 
     * @param pageNo - page number of records to be retrieved.
     * @return page or record to be retrieved.
     */
    @GetMapping
    public ResponseEntity<List<WorkflowEntity>> findAllPage(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy) 
    {
        List<WorkflowEntity> list = this.workflowRepoService.findAll(pageNo, pageSize, sortBy);
    
        return new ResponseEntity<List<WorkflowEntity>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
    
    
    /**
     * Method to retrieve all work flow entity record.
     * 
     * @return all work flow records.
     */
    @RequestMapping(value = "/find/All")
    public List<WorkflowEntity> findAll()
    {
        return this.workflowRepoService.findAll();
    }

    
   /**
    * Method to retrieve work flow entity record by id.
    * 
    * @param workflowId - work flow entity id.
    * @return work flow record returned.
    */
    @RequestMapping(value = "/find/{id}")
    public WorkflowEntity findById(@PathVariable(value = "id") Long id)
    {
        return this.workflowRepoService.findWorkflowById(id);
    }

    
    /**
     * Perform ad hoc work entity list query.
     * 
     * @param name - defined query.
     * @return the list of work flow entity.
     */
    @RequestMapping(value = "/find/{strQuery}")
    public List<WorkflowEntity> findWorkflowByQuery(
                    @PathVariable(value = "strquery") String strQuery)
    {
        return this.workflowRepoService.selectWorkflowQuery(strQuery);
    }
    

    /**
     * Method to create the work flow entity record.
     * 
     * @param workflowEntity - work flow entity to be created.
     * @return work flow entity create response.
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/workflowentity/create")
    public ResponseEntity<WorkflowEntity> createWorkflowEntity(
                    @RequestBody WorkflowEntity workflowEntity)
    {
        return ((HeadersBuilder<BodyBuilder>) ResponseEntity.ok(this.workflowRepoService.createWorkflow(workflowEntity))).build();
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
        WorkflowEntity workflowEntityCheck = this.workflowRepoService.findWorkflowById(workflowentityId);
        
        this.workflowRepoService.updateWorkflow(workflowEntityCheck);
        
        // If the record exists, update.
        if (workflowEntity != null)
        {
            return ((HeadersBuilder<BodyBuilder>) ResponseEntity.ok(this.workflowRepoService.updateWorkflow(workflowEntity))).build();
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
    public ResponseEntity<Void> updateWorkflowEntities(
                    @RequestBody Iterable<? extends WorkflowEntity> workflowEntities)
    {

        // Iterate over the collection of work flow entities and update records.
        for (WorkflowEntity workflowEntity: workflowEntities)
        {
            this.workflowRepoService.updateWorkflow(workflowEntity);
        }
     
        // Return okay.
        return ResponseEntity.ok().build();
    }

    /**
     * Method to delete the work flow entity record by id
     * 
     * @param workflowEntityId - work flow entity id to be deleted.
     */
    @DeleteMapping("/workflowentity/delete/{workflowentityid}")
    public ResponseEntity<Void> deleteWorkflowById(@PathVariable Long workflowEntityId)
    {
        // Delete the work flow entity by id.
        this.workflowRepoService.deleteWorkflowById(workflowEntityId);
        
        // Return okay.
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
        // Delete the work flow entity by work flow entity record.
        this.workflowRepoService.deleteWorkflowByWorkFlow(workflowEntity);  
        
        // Return okay. 
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
        // Delete the collection work flow entity.
        this.workflowRepoService.deleteWorkflowIterable(workflowEntities);

        // Return okay.  
        return ResponseEntity.ok().build();
    }
    
    
    /**
     * Method to delete all of the work flow entity records.
     * 
     */
    @DeleteMapping("/workflowentity/deleteAll")
    public ResponseEntity<Void> deleteWorkflowAll()
    {
        // Delete the All work flow entity records. 
        this.workflowRepoService.deleteWorkflowAll();
        
        // Return okay.
        return ResponseEntity.ok().build();
    }


}
