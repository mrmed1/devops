package com.isamm.tasks.services;

import com.isamm.tasks.dto.LabelDTO;

import java.util.List;


public interface LabelService {
    LabelDTO save(LabelDTO labelDTO) ;
    List<LabelDTO> findAll();
    LabelDTO findOne(Long id);
    void delete(Long id);
}
