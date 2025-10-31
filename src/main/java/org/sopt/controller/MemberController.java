package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.dto.request.MemberCreateRequestDto;
import org.sopt.dto.response.MemberInfoResponseDto;
import org.sopt.global.constant.MemberErrorCode;
import org.sopt.global.exception.MemberException;
import org.sopt.global.response.GlobalBaseResponse;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.sopt.global.constant.MemberSuccessCode.DELETED_MEMBER;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/users")
    public GlobalBaseResponse<MemberInfoResponseDto> createMember(@RequestBody MemberCreateRequestDto requestDto) {
        Long memberId = memberService.join(
                requestDto.name(),
                requestDto.birth(),
                requestDto.email(),
                requestDto.gender()
        );

        MemberInfoResponseDto response = memberService.findOne(memberId)
                .map(MemberInfoResponseDto::from)
                .orElseThrow(() -> new MemberException(MemberErrorCode.INVALID_NAME));

        return GlobalBaseResponse.ok(response);
    }

    @GetMapping("/users/{id}")
    public GlobalBaseResponse<MemberInfoResponseDto> findMemberById(@PathVariable Long id) {
        Member member = memberService.findOne(id)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));

        return GlobalBaseResponse.ok(MemberInfoResponseDto.from(member));
    }

    @GetMapping("/all")
    public GlobalBaseResponse<List<MemberInfoResponseDto>> getAllMembers() {
        List<MemberInfoResponseDto> members = memberService.findAllMembers()
                .stream()
                .map(MemberInfoResponseDto::from)
                .collect(Collectors.toList());
        return GlobalBaseResponse.ok(members);
    }

    @DeleteMapping("/{id}")
    public GlobalBaseResponse<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return GlobalBaseResponse.ok(DELETED_MEMBER);
    }
}