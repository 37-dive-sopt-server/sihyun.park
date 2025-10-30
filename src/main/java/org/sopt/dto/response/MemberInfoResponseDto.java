package org.sopt.dto.response;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;

public record MemberInfoResponseDto(
        Long id,
        String name,
        String email,
        Gender gender
) {
    public static MemberInfoResponseDto from(Member member) {
        return new MemberInfoResponseDto(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getGender()
        );
    }
}