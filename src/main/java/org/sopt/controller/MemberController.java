package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.dto.request.MemberCreateRequestDto;
import org.sopt.dto.response.MemberInfoResponseDto;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/users")
    public MemberInfoResponseDto createMember(@RequestBody MemberCreateRequestDto requestDto) {
        Long memberId = memberService.join(
                requestDto.name(),
                requestDto.birth(),
                requestDto.email(),
                requestDto.gender()
        );

        Optional<Member> created = memberService.findOne(memberId);
        return created.map(MemberInfoResponseDto::from).orElse(null);
    }

    @GetMapping("/users/{id}")
    public MemberInfoResponseDto findMemberById(@PathVariable Long id) {
        Member member = memberService.findOne(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 회원이 존재하지 않습니다."));
        return MemberInfoResponseDto.from(member);
    }

    @GetMapping("/users/all")
    public List<MemberInfoResponseDto> getAllMembers() {
        return memberService.findAllMembers()
                .stream()
                .map(MemberInfoResponseDto::from)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/users/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "회원이 성공적으로 삭제되었습니다.";
    }
}