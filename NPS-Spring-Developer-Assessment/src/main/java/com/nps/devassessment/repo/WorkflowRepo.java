package com.nps.devassessment.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.nps.devassessment.entity.WorkflowEntity;

/**
 * Copyright 2021
 * 
 * File Name:   WorkflowRepo.java
 * 
 * Purpose  : Class for defining the workflow entity repository functionality.
 *            
 * Version
 *    1.0   16 Jun 2021 - initial release.
 * 
 * @author mccormam
 * 
 */
@Repository
public interface WorkflowRepo
                extends PagingAndSortingRepository<WorkflowEntity, Long>
{
    /**
     *  Method to execute composed query string
     * @param strQuery
     * @return
     */
    List<WorkflowEntity> executeQuery(String strQuery);
    
    /**
     * Method to execute full query string
     * @param strQuery
     * @return
     */
    List<WorkflowEntity> fullQuery(String strQuery);
    
}
