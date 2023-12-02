package com.kanban.backend.repository;

import com.kanban.backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
    List<Task> findByStageId(Long stageId);

    List<Task> findByProjectIdAndFeatureId(Long projectId, Long featureId);

}
