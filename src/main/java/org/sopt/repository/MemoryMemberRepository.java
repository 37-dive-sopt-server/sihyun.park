package org.sopt.repository;

import org.sopt.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryMemberRepository extends JpaRepository<Member, Long> {
}