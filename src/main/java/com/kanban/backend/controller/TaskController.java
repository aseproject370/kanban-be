package com.kanban.backend.controller;

import com.kanban.backend.controller.DTO.CreateTaskDTO;
import com.kanban.backend.exception.ResourceNotFound;
import com.kanban.backend.model.Feature;
import com.kanban.backend.model.Project;
import com.kanban.backend.model.Stage;
import com.kanban.backend.model.Task;
import com.kanban.backend.repository.FeatureRepository;
import com.kanban.backend.repository.ProjectRepository;
import com.kanban.backend.repository.StageRepository;
import com.kanban.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @GetMapping("/tasks")
    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getTasksByProject(@PathVariable("id") long pid,@RequestParam Long fid){
        return taskRepository.findByProjectIdAndFeatureId(pid,fid);
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody CreateTaskDTO dto){

        Task task = new Task();

        task.setName(dto.getName());
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new ResourceNotFound("Not found Project with id"));
        Stage stage = stageRepository.findById(dto.getStageId())
                .orElseThrow(() -> new ResourceNotFound("Not found Stage with id "));

        Feature feature = featureRepository.findById(dto.getFeatureId())
                .orElseThrow(() -> new ResourceNotFound("Not found Feature with id "));

        task.setProject(project);
        task.setStage(stage);
        task.setFeature(feature);

        task.toString();

        return taskRepository.save(task);
    }


    @PutMapping("/tasks/{id}")
    public  Task updateTaskName(@PathVariable("id") long id, @RequestBody String name){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));

        existingTask.setName(name);

        return taskRepository.save(existingTask);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return new ResponseEntity<>("{\"message\": \"Task deleted successfully\"}", HttpStatus.OK);
    }

    @PutMapping("/tasks/stage")
    public  Task updateTaskStage(
            @RequestParam(name = "task") long taskId,
            @RequestParam(name = "stage") long stageId
    ){
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + taskId));

        Stage newStage = stageRepository.findById(stageId)
                .orElseThrow(() -> new IllegalArgumentException("Stage not found with id: " + stageId));

        existingTask.setStage(newStage);
        return taskRepository.save(existingTask);
    }


}
