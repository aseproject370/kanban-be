package com.kanban.backend.controller;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kanban.backend.controller.DTO.CreateFeatureDTO;
import com.kanban.backend.exception.ResourceNotFound;
import com.kanban.backend.model.Feature;
import com.kanban.backend.model.FeatureStage;
import com.kanban.backend.model.Project;
import com.kanban.backend.repository.FeatureRepository;
import com.kanban.backend.repository.FeatureStageRepository;
import com.kanban.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class FeatureController {
    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private FeatureStageRepository fsRepository;

    @GetMapping("/{id}/features")
    @JsonBackReference()
    public List<Feature> getAllFeatures(@PathVariable("id") long id){
        System.out.println("here in get features");
        List<Feature> features = featureRepository.findByProjectId(id);
        return features;
    }

    @PostMapping("/{id}/features")
    public Feature createFeature(@RequestBody CreateFeatureDTO dto, @PathVariable("id") long id){

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Not found project with id = " + id));

        Long fsId = dto.getFeatureStageId();

        FeatureStage fs = fsRepository.findById(fsId).orElseThrow(() -> new ResourceNotFound("Not found FeatureStage with id = " + fsId));

        Feature feature = new Feature();

        feature.setName(dto.getName());
        feature.setProject(project);
        feature.setFeatureStage(fs);
        feature.setStartDate(dto.getStartDate());
        feature.setEndDate(dto.getEndDate());

        return featureRepository.save(feature);
    }

    @PutMapping("/features")
    public Feature updateFeatureStage(
            @RequestParam(name = "fid") long fid,
            @RequestParam(name = "sid") long sid
    ){
            System.out.println("here in updateFeatureStage features");

        Feature feature = featureRepository.findById(fid)
                .orElseThrow(() -> new ResourceNotFound("Not found feature with id = " + fid));
        FeatureStage fs = fsRepository.findById(sid).orElseThrow(() -> new ResourceNotFound("Not found FeatureStage with id = " + sid));

        feature.setFeatureStage(fs);
        return featureRepository.save(feature);
    }

    @PutMapping("/features/points")
    public Feature updateStoryPoints(
            @RequestParam(name = "fid") long fid,
            @RequestParam(name = "points") int points
    ){
        System.out.println("here in updateStoryPoints features");

        Feature feature = featureRepository.findById(fid)
                .orElseThrow(() -> new ResourceNotFound("Not found feature with id = " + fid));

        feature.setStoryPoints(points);
        return featureRepository.save(feature);
    }


    @DeleteMapping("/{id}/features")
    public void deleteFeature(@PathVariable("id") long id){
        featureRepository.deleteById(id);
        return;
    }

}
