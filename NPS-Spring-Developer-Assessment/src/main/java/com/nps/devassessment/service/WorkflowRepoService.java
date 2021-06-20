package com.nps.devassessment.service;

import java.util.List;

import com.nps.devassessment.entity.WorkflowEntity;

/**
 * Copyright 2021
 * 
 * File Name:   WorkflowRepoService.java
 * 
 * Purpose  : Interface that defines the functionality of the work flow repository
 *            signature functions.
 *            
 * Version
 *    1.0   16 Jun 2021 - initial release.
 * 
 * @author mccormam
 * 
 */
public interface WorkflowRepoService
{
    /**
     * Find All workflow by submitted query.
     * 
     * @return all work flow entity records.
     * 
     */
    List<WorkflowEntity> findAll();
    
    /**
     * Return work flow records by paging.
     * 
     * @param pageNo - page number
     * @param pageSize - size of the page
     * @param sortBy - sort by column
     * @return work flow records by criteria
     * 
     */
    List<WorkflowEntity> findAll(Integer pageNo,
                    Integer pageSize, String sortBy);
    
    /**
     * Fetch an individual workflow by its 'id'.
     * 
     * @param id - work flow entity record to be retrieved by id.
     * @return work flow entity record.
     * 
     */
    WorkflowEntity findWorkflowById(Long id);
    
    /**
     * Fetch an individual workflow by submitted query
     * 
     * @param strQuery - work flow query string component.
     * @return list of retrieved work flow entity records.
     * 
     */
    List<WorkflowEntity> selectWorkflowQuery(String strQuery);
    
    /**
     * Execute a full query.
     * 
     * @param strQuery - string for full data manipulation functionality.
     * @return list of work flow entities
     * 
     */
    List<WorkflowEntity> fullWorkflowQuery(String strQuery);

    /**
     * Create work flow entity record.
     * 
     * @param workflowEntity - work flow entity record to be created in the database.
     * @return confirm creation of work flow entity record.
     * 
     */
    WorkflowEntity createWorkflow(WorkflowEntity workflowEntity);

    /**
     * Update the work flow entity record.
     * 
     * @param workflowEntity - work flow entity for update.
     * @return confirm update of work flow entity record.
     * 
     */
    WorkflowEntity updateWorkflow(WorkflowEntity workflowEntity);

    /**
     * Delete the work flow entity by id.
     * 
     * @param workflowEntityId - id of work flow record to be deleted.
     * 
     */
    void deleteWorkflowById(Long workflowEntityId);
    
    /**
     * Delete the work flow entity using the from the submitted work flow
     * entity.
     * 
     * @param workflowEntity - work flow entity record to be deleted.
     * 
     */
    void deleteWorkflowByWorkFlow(WorkflowEntity workflowEntity);

    /**
     * Delete the work flow record.
     * 
     * @param workflowEntity - delete work flow entity record.
     * 
     */
    void deleteWorkflow(WorkflowEntity workflowEntity);

    /**
     * Delete collection of work flow entity records.
     * 
     * @param workflowEntity - collection or list of work flow entity records
     *                         to be deleted.
     *                         
     */
    void deleteWorkflowIterable(
                    Iterable<? extends WorkflowEntity> workflowEntity);

    /**
     * Method to delete all of the work flow entity records.
     */
    void deleteWorkflowAll();

}
