package com.kanban.backend.controller;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kanban.backend.exception.ResourceNotFound;
import com.kanban.backend.model.Project;
import com.kanban.backend.model.User;
import com.kanban.backend.repository.ProjectRepository;
import com.kanban.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    //list all projects

    @GetMapping("/{id}/projects")
    @JsonBackReference()
    public List<Project> getAllProjects(@PathVariable("id") Long id){
        System.out.println("here in get projects");
        List<Project> projects = projectRepository.findByUserId(id);
        return projects;
    }

    @PostMapping("/{id}/projects")
    public Project createProject(@RequestBody Project project, @PathVariable("id") long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Not found User with id = " + id));

        project.setUser(user);
        return projectRepository.save(project);
    }

}
