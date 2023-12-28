package com.isamm.tasks.mapper;

import com.isamm.tasks.dto.MemberDTO;
import com.isamm.tasks.models.Member;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface MemberMapper extends EntityMapper<MemberDTO, Member>{

    MemberDTO toDto(Member member);

    Member toEntity(MemberDTO memberDTO);

    default Member fromId(Long id) {
        if (id == null) {
            return null;
        }
        Member member = new Member();
        member.setId(id);
        return member;
    }

}
