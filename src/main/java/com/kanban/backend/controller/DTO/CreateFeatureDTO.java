package com.kanban.backend.controller.DTO;

import java.util.Date;

public class CreateFeatureDTO {
    private String name;

    private Date startDate;

    private Date endDate;

    private Long projectId;

    private Long featureStageId;

    private int storyPoints;

    // Constructors, getters, and setters

    public CreateFeatureDTO() {
        // Default constructor
    }

    public CreateFeatureDTO(String name, Date startDate, Date endDate, Long projectId, Long featureStageId, int storyPoints) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectId = projectId;
        this.featureStageId = featureStageId;
        this.storyPoints = storyPoints;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getFeatureStageId() {
        return featureStageId;
    }

    public void setFeatureStageId(Long featureStageId) {
        this.featureStageId = featureStageId;
    }

    public int getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }
}
