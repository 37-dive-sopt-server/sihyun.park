package org.sopt.controller;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/users")
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

    @GetMapping("/users/{id}")
    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    @GetMapping("/users/all")
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    @DeleteMapping("/users/{id}")
    public void deleteMember(Long memberId) {
        memberService.deleteMember(memberId);
    }
}