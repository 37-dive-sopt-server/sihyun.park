package org.sopt.global.validator;

import org.sopt.domain.Member;
import org.sopt.global.constant.MemberErrorCode;
import org.sopt.global.exception.MemberException;
import org.sopt.repository.MemoryMemberRepository;

import java.util.List;

public final class EmailValidator {
    private EmailValidator() {}

    public static void validateEmailFormat(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new MemberException(MemberErrorCode.INVALID_EMAIL);
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new MemberException(MemberErrorCode.INVALID_FORMAT_EMAIL);
        }
    }

    public static void validateDuplicateEmail(String email, MemoryMemberRepository repository) {
        List<Member> allMembers = repository.findAll();
        boolean exists = allMembers.stream()
                .anyMatch(member -> member.getEmail().equals(email));

        if (exists) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }
    }
}
