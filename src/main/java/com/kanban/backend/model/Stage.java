package com.kanban.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="stages")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name="project_id", nullable=false)
    private Project project;

    public List<Task> getTasks() {
        return tasks;
    }

    @OneToMany(mappedBy = "stage")
    @JsonIgnore
    private List<Task> tasks;


    public Stage() {
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

    @Override
    public String toString() {
        return "Stage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", project=" + project +
                ", tasks=" + tasks +
                '}';
    }

    public Stage(Long id, String name, Project project) {
        this.id = id;
        this.name = name;
        this.project = project;
    }
}
