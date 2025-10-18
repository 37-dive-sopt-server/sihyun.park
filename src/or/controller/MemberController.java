package or.controller;

import or.domain.Gender;
import or.domain.Member;
import or.service.MemberService;

import java.util.List;
import java.util.Optional;

public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    public Long createMember(String name, String birth, String email, Gender gender) {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("⚠️ 이름을 입력해주세요.");
            }
            if (birth == null || birth.trim().isEmpty()) {
                throw new IllegalArgumentException("⚠️ 생년월일을 입력해주세요.");
            }
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("⚠️ 이메일을 입력해주세요.");
            }
            if (gender == null) {
                throw new IllegalArgumentException("⚠️ 성별을 입력해주세요.");
            }

            return memberService.join(name, birth, email, gender);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    public void deleteMember(Long memberId) {
        memberService.deleteMember(memberId);
    }
}