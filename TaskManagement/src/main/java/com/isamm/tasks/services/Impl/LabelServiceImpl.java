package com.isamm.tasks.services.Impl;

import com.isamm.tasks.dto.LabelDTO;
import com.isamm.tasks.mapper.LabelMapper;
import com.isamm.tasks.models.Label;
import com.isamm.tasks.repository.LabelRepository;
import com.isamm.tasks.services.LabelService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private LabelMapper labelMapper;

    @Override
    public LabelDTO save(LabelDTO labelDTO) {
        Label label = labelMapper.toEntity(labelDTO);
        label = labelRepository.save(label);
        return labelMapper.toDto(label);
    }

    @Override
    public List<LabelDTO> findAll() {
        return labelMapper.toDto(labelRepository.findAll());
    }

    @Override
    public LabelDTO findOne(Long id) {
        Optional<Label> label =labelRepository.findById(id);
        return labelMapper.toDto(label.get());
    }

    @Override
    public void delete(Long id) {
        labelRepository.deleteById(id);
    }
}
