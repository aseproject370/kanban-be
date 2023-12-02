package com.kanban.backend.repository;

import com.kanban.backend.model.FeatureStage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureStageRepository extends JpaRepository<FeatureStage, Long> {

}