package or.service;

import or.domain.Gender;
import or.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Long join(String name,String birth,String email, Gender gender);
    Optional<Member> findOne(Long memberId);
    List<Member> findAllMembers();
    void deleteMember(Long memberId);
    boolean validEmail(String email);
    int getAge(String birth);
}