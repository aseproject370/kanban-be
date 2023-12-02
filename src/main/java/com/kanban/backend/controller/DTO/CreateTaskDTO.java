package com.kanban.backend.controller.DTO;

public class CreateTaskDTO {
    private String name;
    private Long projectId;
    private Long stageId;

    private Long FeatureId;

    // Default constructor
    public CreateTaskDTO() {
    }

    // Parameterized constructor
    public CreateTaskDTO(String name, Long projectId, Long stageId, Long featureId) {
        this.name = name;
        this.projectId = projectId;
        this.stageId = stageId;
        FeatureId = featureId;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getFeatureId() {
        return FeatureId;
    }

    public void setFeatureId(Long featureId) {
        FeatureId = featureId;
    }
}
