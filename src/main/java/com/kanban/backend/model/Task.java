package com.kanban.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="tasks")
public class Task {

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Task(Long id, String name, Project project, Stage stage, Feature feature) {
        this.id = id;
        this.name = name;
        this.project = project;
        this.stage = stage;
        this.feature = feature;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name="project_id", nullable=false)
    @JsonIgnore
    private Project project;

    @ManyToOne
    @JoinColumn(name = "stage_id", unique = false)
    private Stage stage;

    @ManyToOne
    @JoinColumn(name = "feature_id")
    @JsonIgnore
    private Feature feature;

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
