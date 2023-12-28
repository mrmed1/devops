package com.isamm.tasks.controllers;

import com.isamm.tasks.dto.LabelDTO;
import com.isamm.tasks.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
public class LabelController {

	@Autowired
	private LabelService labelService;


    @PostMapping
    public ResponseEntity<LabelDTO> create(@RequestBody LabelDTO labelDTO) {
        LabelDTO label = labelService.save(labelDTO);
        return ResponseEntity.ok().body(label);
    }

    @GetMapping
    public ResponseEntity<List<LabelDTO>> findAll() {
        List<LabelDTO> labels = labelService.findAll();
        return ResponseEntity.ok().body(labels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabelDTO> findOne(@PathVariable Long id) {
        LabelDTO label = labelService.findOne(id);
        return ResponseEntity.ok().body(label);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        labelService.delete(id);
        return ResponseEntity.ok().build();
    }
}
