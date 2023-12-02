package com.kanban.backend.repository;

import com.kanban.backend.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findByProjectId(Long projectId);
}
