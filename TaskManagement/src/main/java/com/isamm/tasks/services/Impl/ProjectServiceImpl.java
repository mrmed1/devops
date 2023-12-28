package com.isamm.tasks.services.Impl;

import com.isamm.tasks.dto.ProjectDTO;
import com.isamm.tasks.mapper.ProjectMapper;
import com.isamm.tasks.models.Project;
import com.isamm.tasks.repository.ProjectRepository;
import com.isamm.tasks.services.ProjectService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;


    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {
        Project project = projectMapper.toEntity(projectDTO);
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return projectMapper.toDto(projectRepository.findAll());
    }

    @Override
    public ProjectDTO findOne(Long id) {
          Optional<Project> project =projectRepository.findById(id);
            return projectMapper.toDto(project.get());
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
