package com.isamm.tasks.services;


import com.isamm.tasks.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {
    ProjectDTO save(ProjectDTO projectDTO) ;
    List<ProjectDTO> findAll();
    ProjectDTO findOne(Long id);
    void delete(Long id);
}
