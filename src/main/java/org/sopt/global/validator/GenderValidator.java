package org.sopt.global.validator;


import org.sopt.domain.Gender;
import org.sopt.global.constant.MemberErrorCode;
import org.sopt.global.exception.MemberException;

public final class GenderValidator {
    private GenderValidator() {}

    public static void validate(Gender gender) {
        if (gender == null) {
            throw new MemberException(MemberErrorCode.INVALID_GENDER);
        }
    }
}