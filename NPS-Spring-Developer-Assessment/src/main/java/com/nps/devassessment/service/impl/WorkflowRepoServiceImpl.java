package com.nps.devassessment.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.repo.WorkflowRepo;
import com.nps.devassessment.service.WorkflowRepoService;

/**
 * Copyright 2021
 * 
 * File Name:   WorkflowRepoServiceImpl.java
 * 
 * Purpose  : Class for defining the methods concerning the implementation
 *            of the workflow entity service functionality. This establishes
 *            the concrete implementation of the CRUD to retrieve, delete,
 *            update and create work flow entity records.
 *            
 * Version
 *    1.0   16 Jun 2021 - initial release.
 * 
 * @author mccormam
 * 
 */
@Service
public class WorkflowRepoServiceImpl implements WorkflowRepoService
{
    @PersistenceContext()
    private EntityManager em;
    
    private WorkflowRepo workflowRepo;


    @Autowired
    WorkflowRepoServiceImpl(WorkflowRepo workflowRepo)
    {
        this.workflowRepo = workflowRepo;
    }

    /**
     * Method to perform query on the Workflow Entity based on workflow id.
     * 
     */
    @Override
    public WorkflowEntity findWorkflowById(Long id)
    {
        return this.workflowRepo.findById(id).orElse(null);
    }
    
    
    /**
     * Method to perform query on the Workflow Entity. Query consist 
     * of all columns that are required to be retrieved moderated by 
     * WHERE clause.
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<WorkflowEntity> selectWorkflowQuery(String strQuery)
    {
        List<WorkflowEntity> lstWorkflow = null;
        
        // Check if the declension clause is not null and not blank.
        // Add the WHERE declension clause to the required query.
        if ((strQuery != null) && (!strQuery.equals("")))
        {
            lstWorkflow = em.createNamedQuery("Workflow.selectQuery")
                            .setParameter("strquery", strQuery)
                            .getResultList();
        }
        // Otherwise there is no declension clause - there is no WHERE 
        // declension clause to the required query
        else
        {
            lstWorkflow = em.createNamedQuery("Workflow.findAll")
                            .getResultList();
        }
        
        // Return result
        return lstWorkflow;
    }
    
    
    /**
     * Method to perform selective query on the Workflow Entity. Only 
     * limited by selection of columns that are required.
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<WorkflowEntity> fullWorkflowQuery(String strQuery)
    {
        List<WorkflowEntity> lstWorkflow = null;
        
        // Check if the declension clause is not null and not blank.
        // Add the WHERE declension clause to the required query.
        if ((strQuery != null) && (!strQuery.equals("")))
        {
            lstWorkflow = em.createNamedQuery("Workflow.fullQuery")
                            .setParameter("strquery", strQuery)
                            .getResultList();
        }
        
        // Return result
        return lstWorkflow;
    }

    
    /**
     * Method to retrieve all work flow records.
     * 
     */
    @Override
    public List<WorkflowEntity> findAll()
    {
        // Retrieve all workflow records
        @SuppressWarnings("unchecked")
        List<WorkflowEntity> lstWorkflow = em.createNamedQuery("Workflow.findAll")
                            .getResultList();
        
        // Return result
        return lstWorkflow;
    }


    /**
     * Method to retrieve all work flow entity records and paginate.
     */
    @Override
    public List<WorkflowEntity> findAll(Integer pageNo,
                    Integer pageSize, String sortBy)
    {
        // Set paging of workflow records.
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        // Set work flow entity record page
        Page<WorkflowEntity> pagedResult = this.workflowRepo.findAll(paging);

        // If the returned page result has records to display, return record page.
        if (pagedResult.hasContent())
        {
            return pagedResult.getContent();
        }
        // Otherwise, no further records, return null array list.
        else
        {
            return new ArrayList<WorkflowEntity>();
        }
    }
    
    /**
     * Method to create the work flow entity record.
     */
    @Override
    public WorkflowEntity createWorkflow(WorkflowEntity workflowEntity)
    {
        // Check that the workflow entity exists - not null.
        if (workflowEntity != null)
        {
            // Check if the record does not exist - create/save the record.
            if (!this.workflowRepo.existsById(workflowEntity.getWorkflowId()))
            { 
                return this.workflowRepo.save(workflowEntity);
            }
        }
        
        // Create new record criteria fails - return null;
        return null;
    } 
 
    
    /**
     * Method to update the work flow entity record by id.
     * 
     */
    @Override
    public WorkflowEntity updateWorkflow(WorkflowEntity workflowEntity)
    {
        // Check that the workflow entity exists - not null.
        if (workflowEntity != null)
        {
            // Check if the record does exist - update the record.
            if (this.workflowRepo.existsById(workflowEntity.getWorkflowId()))
            { 
                return this.workflowRepo.save(workflowEntity);
            }
        }
        
        // Update record criteria fails - return null;
        return null;
    }
    
    
    /**
     * Method to delete the work flow entity record by id
     * 
     * @param workflowEntity - work flow entity to be deleted with id.
     * 
     */
    @Override
    public void deleteWorkflowById(Long workflowEntityId)
    {
        // Check if the record does exist - update the record.
        if (this.workflowRepo.existsById(workflowEntityId))
        { 
            this.workflowRepo.deleteById(workflowEntityId);
        }

    }
    
    /**
     * Method to delete the work flow entity record by work flow entity
     * 
     * @param workflowEntity - work flow entity to be deleted by work flow entity.
     * 
     */
    @Override
    public void deleteWorkflowByWorkFlow(WorkflowEntity workflowEntity)
    {
        // Check that the workflow entity exists - not null.
        if (workflowEntity != null)
        {
            // Check if the record does exist - update the record.
            if (this.workflowRepo.existsById(workflowEntity.getWorkflowId()))
            { 
                this.workflowRepo.deleteById(workflowEntity.getWorkflowId());
            }
        }

    }
    
    
    /**
     * Method to delete the work flow entity record.
     * 
     * @param workflowEntity - work flow entity to be deleted
     * 
     */
    @Override
    public void deleteWorkflow(WorkflowEntity workflowEntity)
    {
        // Check that the workflow entity exists - not null.
        if (workflowEntity != null)
        {
            // Check if the record does exist - update the record.
            if (this.workflowRepo.existsById(workflowEntity.getWorkflowId()))
            { 
                this.workflowRepo.delete(workflowEntity);
            }
        }
        
    }
    
    
    /**
     * Method to delete collection of work flow entity records.
     * 
     * @param workflowEntity - collection of work flow entity to be deleted
     * 
     */
    public void deleteWorkflowIterable(Iterable<? extends WorkflowEntity> workflowEntity)
    {
        // Check that the workflow entity exists - not null.
        if (workflowEntity != null)
        {
            this.workflowRepo.deleteAll((Iterable<? extends WorkflowEntity>) workflowEntity);
        }
        
    }
    
    /**
     * Method to delete all of the work flow entity records.
     * 
     */
    @Override
    public void deleteWorkflowAll()
    {           
        this.workflowRepo.deleteAll();   
    }
    
}
