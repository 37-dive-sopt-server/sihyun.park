package or.controller;

import or.domain.Member;
import or.service.MemberServiceImpl;

import java.util.List;
import java.util.Optional;

public class MemberController {

    private MemberServiceImpl memberService = new MemberServiceImpl();

    public Long createMember(String name, String birth, String email, Member.Gender gender) {
        return memberService.join(name,birth,email,gender);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }
}