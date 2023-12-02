package com.kanban.backend.controller;

import com.kanban.backend.controller.DTO.CreateStageDTO;
import com.kanban.backend.exception.ResourceNotFound;
import com.kanban.backend.model.Project;
import com.kanban.backend.model.Stage;
import com.kanban.backend.repository.ProjectRepository;
import com.kanban.backend.repository.StageRepository;
import com.kanban.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")

public class StageController {

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/stages")
    public List<Stage> getAllStages(){
        return stageRepository.findAll();
    }

    @GetMapping("/{id}/stages")
    public List<Stage> getStagesByProject(@PathVariable("id") long id){
        return stageRepository.findByProjectId(id);
    }

    @PostMapping("/stages")
    public Stage createStage(@RequestBody CreateStageDTO dto){

        System.out.println(dto.toString());

        Stage stage = new Stage();
        stage.setName(dto.getName());

        Project project = projectRepository.findById(dto.getProjectId()).orElseThrow(()->
            new ResourceNotFound("project not found with id "+dto.getProjectId())
        );

        stage.setProject(project);

        System.out.println(stage.toString());
        return stageRepository.save(stage);
    }


    @PutMapping("/stages/{id}")
    public Stage updateStageName(@PathVariable("id") long id, @RequestBody String name){
        Stage existingStage = stageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Stage not found with id: " + id));

        existingStage.setName(name);

        return stageRepository.save(existingStage);
    }

    @DeleteMapping("stages/{id}")
    public ResponseEntity<String> deleteStage(@PathVariable Long id) {
        Optional<Stage> optionalStage = stageRepository.findById(id);
        if (optionalStage.isPresent()) {
            Stage stage = optionalStage.get();
            taskRepository.deleteAll(stage.getTasks());
            stageRepository.deleteById(id);
        }
        return new ResponseEntity<>("{\"message\": \"Stage deleted successfully\"}", HttpStatus.OK);
    }

}
