package com.nps.devassessment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Copyright 2021
 * 
 * File Name:   WorkflowEntity.java
 * 
 * Purpose  : Class to define the workflow entity record. This class
 *            includes defined named queries with replacement parameters.
 *            
 * Version
 *    1.0   16 Jun 2021 - initial release.
 * 
 * @author mccormam
 * 
 */
@Entity
@Table(name = "Workflow")
@NamedQuery(name = "Workflow.findAll", query = "SELECT w FROM WorkflowEntity w")
@NamedNativeQueries(value = {
    @NamedNativeQuery(name = "Workflow.selectQuery", query = "SELECT w FROM WorkflowEntity w :strquery"),
    @NamedNativeQuery(name = "Workflow.fullQuery", query = ":strquery")

}) 
public class WorkflowEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    private Long      id;

    @Column(name = "workflow_id")
    private Long      workflowId;

    @Column(name = "kpf_confirmed", nullable = false)
    private Boolean   kpfConfirmed = Boolean.FALSE;

    @Column(name = "yjb_yp_id", nullable = false)
    private Long      yjbYp;

    @Column(name = "workflow_state")
    private String    workflowState;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "modified")
    private Timestamp modified;

    @Column(name = "created_by")
    private String    createdBy;

    @Column(name = "modified_by")
    private String    modifiedBy;

    @Column(name = "metadata")
    private String    metadata;

    @Column(name = "process")
    private String    process;

    @Column(name = "task_id")
    private String    taskId;

    @Column(name = "previous_state")
    private String    previousState;

    @Column(name = "task_status")
    private String    taskStatus;

    @Column(name = "task_metadata")
    private String    taskMetadata;


    public WorkflowEntity()
    {
    }


    public Long getYjbYp()
    {
        return yjbYp;
    }


    public void setYjbYp(Long yjbYp)
    {
        this.yjbYp = yjbYp;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public Long getWorkflowId()
    {
        return workflowId;
    }


    public void setWorkflowId(Long workflowId)
    {
        this.workflowId = workflowId;
    }


    public Boolean getKpfConfirmed()
    {
        return kpfConfirmed;
    }


    public void setKpfConfirmed(Boolean kpfConfirmed)
    {
        this.kpfConfirmed = kpfConfirmed;
    }


    public String getWorkflowState()
    {
        return workflowState;
    }


    public void setWorkflowState(String workflowState)
    {
        this.workflowState = workflowState;
    }


    public Timestamp getCreated()
    {
        return created;
    }


    public void setCreated(Timestamp created)
    {
        this.created = created;
    }


    public Timestamp getModified()
    {
        return modified;
    }


    public void setModified(Timestamp modified)
    {
        this.modified = modified;
    }


    public String getCreatedBy()
    {
        return createdBy;
    }


    public void setCreatedBy(String created_by)
    {
        this.createdBy = created_by;
    }


    public String getModifiedBy()
    {
        return modifiedBy;
    }


    public void setModifiedBy(String modified_by)
    {
        this.modifiedBy = modified_by;
    }


    public String getMetadata()
    {
        return metadata;
    }


    public void setMetadata(String metadata)
    {
        this.metadata = metadata;
    }


    public String getProcess()
    {
        return process;
    }


    public void setProcess(String process)
    {
        this.process = process;
    }


    public String getTaskId()
    {
        return taskId;
    }


    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }


    public String getPreviousState()
    {
        return previousState;
    }


    public void setPreviousState(String previousState)
    {
        this.previousState = previousState;
    }


    public String getTaskStatus()
    {
        return taskStatus;
    }


    public void setTaskStatus(String taskStatus)
    {
        this.taskStatus = taskStatus;
    }


    public String getTaskMetadata()
    {
        return taskMetadata;
    }


    public void setTaskMetadata(String taskMetadata)
    {
        this.taskMetadata = taskMetadata;
    }
}
