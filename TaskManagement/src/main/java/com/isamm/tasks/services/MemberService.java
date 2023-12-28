package com.isamm.tasks.services;


import com.isamm.tasks.dto.MemberDTO;
import com.isamm.tasks.dto.ProjectDTO;

import java.util.List;


public interface MemberService {


    MemberDTO save(MemberDTO memberDTO) ;


    List<MemberDTO> findAll();

    MemberDTO findOne(Long id);

    void delete(Long id);

    public List<ProjectDTO> getAllProjectsAndTasksByUsername(String username);

}
