package com.isamm.tasks.repository;

import com.isamm.tasks.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	// List<Member> findByUsername(String username);
	Optional<Member> findByUsername(String username);


}