package com.isamm.tasks.mapper;

import com.isamm.tasks.dto.ProjectDTO;
import com.isamm.tasks.models.Project;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {TaskMapper.class, MemberMapper.class})
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {

    ProjectDTO toDto(Project project);

    Project toEntity(ProjectDTO projectDTO);

    default Project fromId(Long id) {
        if (id == null) {
            return null;
        }
        Project project = new Project();
        project.setId(id);
        return project;
    }

}
