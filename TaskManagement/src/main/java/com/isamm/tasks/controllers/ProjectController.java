package com.isamm.tasks.controllers;

import com.isamm.tasks.dto.ProjectDTO;
import com.isamm.tasks.dto.TaskDTO;
import com.isamm.tasks.services.ProjectService;
import com.isamm.tasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;
    
    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO ProjectDTO) {
        ProjectDTO Project = projectService.save(ProjectDTO);
        return ResponseEntity.ok().body(Project);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> findAllProjects() {
        List<ProjectDTO> Projects = projectService.findAll();
        return ResponseEntity.ok().body(Projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findOneProject(@PathVariable Long id) {
        ProjectDTO Project = projectService.findOne(id);
        return ResponseEntity.ok().body(Project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO ProjectDTO) {
        if (ProjectDTO.getId() == null) {
            return createProject(ProjectDTO);
        }else {
            ProjectDTO Project = projectService.save(ProjectDTO);
            return ResponseEntity.ok().body(Project);
        }
    }
    @PutMapping("/{projectId}/assignTask/{taskId}")
    public ResponseEntity<ProjectDTO> assignTaskToProject(@PathVariable Long projectId, @PathVariable Long taskId) {
        ProjectDTO project = projectService.findOne(projectId);
        TaskDTO task = taskService.findOne(taskId);
        if (project != null && task != null) {            
            List<TaskDTO> projectTasks = project.getTasks();
            projectTasks.add(task);            
            project.setTasks(projectTasks);
            projectService.save(project);
            return ResponseEntity.ok().body(project);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}
