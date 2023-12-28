package com.isamm.tasks.mapper;

import com.isamm.tasks.dto.TaskDTO;
import com.isamm.tasks.models.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {LabelMapper.class, ProjectMapper.class})
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {

    @Mapping(source ="project.id", target = "projectId")
    @Mapping(source ="project.name", target = "projectName")
    TaskDTO toDto(Task task);

    @Mapping(source = "projectId", target = "project")
    Task toEntity(TaskDTO taskDTO);

    default Task fromId(Long id) {
        if (id == null) {
            return null;
        }
        Task task = new Task();
        task.setId(id);
        return task;
    }

}
