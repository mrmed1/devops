package com.isamm.tasks.services;

import com.isamm.tasks.dto.TaskDTO;
import com.isamm.tasks.models.Label;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface TaskService {

    TaskDTO save (TaskDTO taskDTO);
    List<TaskDTO> findAll();
    TaskDTO findOne(Long id);
    void delete(Long id);
	List<TaskDTO> getTasksByProject(Long projectId);
	List<TaskDTO> getTasksByLabel(Long labelId);
    TaskDTO toTrash(Long id);
    TaskDTO toListTask(Long id);
	List<TaskDTO> getTasksByLabelFiltred(Long labelId);
	List<TaskDTO> getTasksByLabelAndProject(Long labelId, Long projectId);
	List<TaskDTO> getTasksByDueDate(LocalDate dueDate);
	List<TaskDTO> getTasksByDueDateAndProjectSortedByDueDateDescending(LocalDate dueDate, Long projectId);
	List<TaskDTO> getTasksByStartDateAndProjectSortedByStartDateAscending(LocalDate startDate, Long projectId);
	List<TaskDTO> getTasksByCompletedAndProjectSortedByDueDateAscending(Boolean completed, Long projectId);
	Map<Label, Long> countTasksByProjectId(Long projectId);
	List<TaskDTO> searchTasksByName(String keyword);
	 List<TaskDTO> filter(List<Long> labelIds, Long projectId, String keyword, Boolean completed,
		        LocalDate minStartDate, LocalDate maxStartDate, LocalDate minDueDate, LocalDate maxDueDate);

}
