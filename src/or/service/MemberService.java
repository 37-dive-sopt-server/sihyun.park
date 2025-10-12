package or.service;

import or.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Long join(String name,String birth,String email,String gender);
    Optional<Member> findOne(Long memberId);
    List<Member> findAllMembers();
    void deleteMember(Long memberId);
}
