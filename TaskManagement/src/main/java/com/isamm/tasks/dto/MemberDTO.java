package com.isamm.tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String username;
    private List<ProjectDTO> projects;
}
