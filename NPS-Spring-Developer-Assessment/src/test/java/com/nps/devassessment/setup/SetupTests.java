package com.nps.devassessment.setup;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.service.WorkflowRepoService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.NotYetImplementedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright 2021
 * 
 * File Name:   SetupTests.java
 * 
 * Purpose  : Class to define the workflow entity set up test
 *            
 * Version
 *    1.0   16 Jun 2021 - initial release.
 * 
 * @author mccormam
 * 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SetupTests
{
    private static final Logger log = LoggerFactory.getLogger(SetupTests.class);
    
//    @PersistenceContext()
//    private EntityManager em;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private static final String IN_PROGRESS = "IN PROGRESS";
    private static final String CANCELLED = "CANCELLED";
    private static final String ADMITTED = "ADMITTED";
    
    private static final String TEST1A_WORKFLOW_QUERY = "SELECT workflows w WHERE w.workflow_state IN ('IN PROGRESS', 'CANCELLED', 'ADMITTED')";

    @Autowired
    private WorkflowRepoService workflowRepoService;
    
    private Session getCurrentSession() 
    {
        return this.sessionFactory.getCurrentSession();
    }



    // NOTE - This is a sample
    @Test
    public void test0_shouldProvideASampleOfAWorkingRepoCall()
    {

        // start test
        log.info("Starting test0 to demonstrate working repo call...");
        WorkflowEntity workflowEntity = this.workflowRepoService
                        .findWorkflowById(66176L);

        // Assert
        Assert.assertNotNull(workflowEntity);
        Assert.assertEquals("IN PROGRESS", workflowEntity.getWorkflowState());

        // end test
        log.info("Workflow {} found.  yjb_yp_id = {}, workflow_state = {}", workflowEntity
                        .getId(), workflowEntity.getYjbYp(), workflowEntity
                                        .getWorkflowState());
        log.info("test0 complete");
    }


    /**
     * Implement queries as per the word document. Assert that the results of each
     * of the query calls is not null/empty write the count of each of the queries
     * to the log.
     * 
     */
    @Test
    public void test1_shouldDemonstrateRequestedRepoQueries()
    {
        // TEST 1a
        // Select workflows by workflow_state = a given status (e.g. “IN
        // PROGRESS”, “CANCELLED”, “ADMITTED”)        
        log.info("Starting test1a to demonstrate working repo query...");
        
        String strQuery01 = "WHERE w.workflow_state IN ('IN PROGRESS', 'CANCELLED', 'ADMITTED')";
        
        List<WorkflowEntity> lstWorkflowEntity01 = this.workflowRepoService.selectWorkflowQuery(strQuery01);
        
        // Assert
        Assert.assertNotNull(lstWorkflowEntity01);
      //  Assert.assertEquals("IN PROGRESS", workflowEntity.getWorkflowState());

        // end test
        log.info("Workflow {} found.", lstWorkflowEntity01.size());
        log.info("test1a complete");
        
        // TEST 1b
        // Select workflows by a given list of yjb_yp_id values (e.g. 30848,
        // 32524, 28117)
        log.info("Starting test1b to demonstrate working repo query for value of yjb_yp_id...");
        
        String strQuery02 = "WHERE yjb_yp_id IN (30848, 32524, 28117)";
        
        List<WorkflowEntity> lstWorkflowEntity02 = this.workflowRepoService.selectWorkflowQuery(strQuery02);
        
        // Assert
        Assert.assertNotNull(lstWorkflowEntity02);
      //  Assert.assertEquals("IN PROGRESS", workflowEntity.getWorkflowState());

        // end test
        log.info("Workflow {} found.", lstWorkflowEntity02.size());
        log.info("test1b complete");

        // TEST 1c
        // Select workflows by 'created' column is after a given date (e.g.
        // 01/02/2021)        
        String strQuery03 = "WHERE created '01/02/2021'";
        
        List<WorkflowEntity> lstWorkflowEntity03 = this.workflowRepoService.selectWorkflowQuery(strQuery03);
        
        // Assert
        Assert.assertNotNull(lstWorkflowEntity03);
      //  Assert.assertEquals("IN PROGRESS", workflowEntity.getWorkflowState());

        // end test
        log.info("Workflow {} found.", lstWorkflowEntity03.size());
        log.info("test1c complete");
        
        // TEST 1d
        // Select workflows by 'modified' column is after a given date (e.g.
        // 01/01/20) but before another given date (e.g. 01/03/2021)
        String strQuery04 = "WHERE modifed BETWEEN '01/01/2020' AND '01/03/2021'";
        
        List<WorkflowEntity> lstWorkflowEntity04 = this.workflowRepoService.selectWorkflowQuery(strQuery04);
        
        // Assert
        Assert.assertNotNull(lstWorkflowEntity04);
      //  Assert.assertEquals("IN PROGRESS", workflowEntity.getWorkflowState());

        // end test
        log.info("Workflow {} found.", lstWorkflowEntity04.size());
        log.info("test1d complete");
        
        // TEST 1e
        // Select workflows by process = a given value (e.g. “placementProcess”)
        // AND task_status != a given value (e.g. “ADMITTED”)
        String strQuery05 = "WHERE process = 'placementProcess' AND task_status != 'ADMITTED'";
        
        List<WorkflowEntity> lstWorkflowEntity05 = this.workflowRepoService.selectWorkflowQuery(strQuery05);
        
        // Assert
        Assert.assertNotNull(lstWorkflowEntity05);
      //  Assert.assertEquals("IN PROGRESS", workflowEntity.getWorkflowState());

        // end test
        log.info("Workflow {} found.", lstWorkflowEntity05.size());
        log.info("test1e complete");
        
        // TEST 1f
        // Select id, yjb_yp_id and task_status columns for all workflows where
        // created_by = a given value (e.g. “lee.everitt”)
        String strQuery06 = "SELECT yjb_yp_id, task_status WHERE created_by = 'lee.everitt'";
        
        List<WorkflowEntity> lstWorkflowEntity06 = this.workflowRepoService.selectWorkflowQuery(strQuery06);
        
        // Assert
        Assert.assertNotNull(lstWorkflowEntity06);
      //  Assert.assertEquals("IN PROGRESS", workflowEntity.getWorkflowState());

        // end test
        log.info("Workflow {} found.", lstWorkflowEntity06.size());
        log.info("test1f complete");
        
        // TEST 1g
        // Select the first 10 rows where process = a given value (e.g.
        // “transferPlanned”). Order the results by id in descending order
        String strQuery07 = "WHERE process = 'transferPlanned' AND task_status != 'ADMITTED'";
 
        List<WorkflowEntity> lstWorkflowEntity07 = this.workflowRepoService.selectWorkflowQuery(strQuery07);
        
        // Assert
        Assert.assertNotNull(lstWorkflowEntity07);
      //  Assert.assertEquals("IN PROGRESS", workflowEntity.getWorkflowState());

        // end test
        log.info("Workflow {} found.", lstWorkflowEntity07.size());
        log.info("test1g complete");
    }


    /**
     * Test to demonstrate pagination through the entire workflow repo using
     * a page size of 20.
     * 
     * For each page: the count of each distinct workflow_status is written 
     * to the log.
     * 
     * Once the entire table has been paged through, the number of pages is
     * written to the log.
     */
    @Test
    public void test2_shouldDemonstratePageableRepoCapability()
    {
        log.info("Starting test2 to demonstrate working pagination...");
        
        // Set paging parameters and sort criteria
        Integer pageNo = 0;
        Integer pageSize = 50;
        String sortBy = "workflowId";
        
        // Retrieve workflow records according to paging values.
        List<WorkflowEntity> lstWorkflowEntity = this.workflowRepoService.findAll(pageNo, pageSize, sortBy);
        
        // Calculate the number of work flow entity records.
        int noRecords = lstWorkflowEntity.size();
        
        // Calculate the number of pages - (number of records / number of records per page).
        int noPages = (noRecords/pageSize);
        
        // Assert
        Assert.assertNotNull(lstWorkflowEntity);

        // end test
        log.info("Workflow Number of Records: {} found. Number of records per Page {}. Number of Pages {}", noRecords, pageSize, noPages);
        log.info("test2 complete");
    }
    
}
