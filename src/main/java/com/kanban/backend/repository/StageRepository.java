package com.kanban.backend.repository;

import com.kanban.backend.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByProjectId(Long projectId);
}
