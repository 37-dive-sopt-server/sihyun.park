package org.sopt.dto.request;

import org.sopt.domain.Gender;

public record MemberCreateRequestDto(String name,
                                     String birth,
                                     String email,
                                     Gender gender) {
}