package com.kanban.backend.controller.DTO;

public class CreateStageDTO {

    private String name;
    private Long projectId;

    // Constructors

    @Override
    public String toString() {
        return "CreateStageDTO{" +
                "name='" + name + '\'' +
                ", projectId=" + projectId +
                '}';
    }

    public CreateStageDTO() {
        // Default constructor
    }

    public CreateStageDTO(String name, Long projectId) {
        this.name = name;
        this.projectId = projectId;
    }

    // Getters and Setters

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
}

