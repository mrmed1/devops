package com.isamm.tasks.mapper;

import com.isamm.tasks.dto.LabelDTO;
import com.isamm.tasks.models.Label;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface LabelMapper extends EntityMapper<LabelDTO, Label>
{
    LabelDTO toDto(Label label);

    Label toEntity(LabelDTO labelDTO);

    default Label fromId(Long id) {
        if (id == null) {
            return null;
        }
        Label label = new Label();
        label.setId(id);
        return label;
    }

}
