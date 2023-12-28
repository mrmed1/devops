package com.isamm.tasks.services.Impl;
import com.isamm.tasks.dto.MemberDTO;
import com.isamm.tasks.dto.ProjectDTO;
import com.isamm.tasks.mapper.MemberMapper;
import com.isamm.tasks.models.Member;
import com.isamm.tasks.repository.MemberRepository;
import com.isamm.tasks.services.MemberService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        Member member = memberMapper.toEntity(memberDTO);
        member = memberRepository.save(member);
        return memberMapper.toDto(member);
    }


    @Override
    public List<MemberDTO> findAll() {
    return memberMapper.toDto(memberRepository.findAll());
    }

    @Override
    public MemberDTO findOne(Long id) {
        Optional<Member> member =memberRepository.findById(id);
        return memberMapper.toDto(member.get());
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

   /* @Override
    public List<ProjectDTO> getAllProjectsAndTasksByUsername(String username) {
        List<Member> members = memberRepository.findByUsername(username);

        if (!members.isEmpty()) {
            Member member = members.get(0); // Assuming username is unique

            return memberMapper.toDto(member).getProjects();
        }

        return Collections.emptyList();
    }*/

    @Override
    public List<ProjectDTO> getAllProjectsAndTasksByUsername(String username) {
	
	   Optional<Member> members = memberRepository.findByUsername(username);

        if (!members.isEmpty()) {
            Member member = members.get(); 

            return memberMapper.toDto(member).getProjects();
        }

        return Collections.emptyList();
    }

}
