package com.kanban.backend.model;

import jakarta.persistence.*;

enum FeatureStageName {
    TODO,
    IN_PROGRESS,
    DONE
}


@Entity
public class FeatureStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FeatureStageName name;

    // Constructors, getters, and setters

    public FeatureStage() {
        // Default constructor
    }

    public FeatureStage(FeatureStageName name) {
        this.name = name;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FeatureStageName getName() {
        return name;
    }

    public void setName(FeatureStageName name) {
        this.name = name;
    }
}
