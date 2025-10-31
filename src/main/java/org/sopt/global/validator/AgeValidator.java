package org.sopt.global.validator;

import org.sopt.global.constant.MemberErrorCode;
import org.sopt.global.exception.MemberException;

public final class AgeValidator {
    private AgeValidator() {}


    public static void validateAge(int age) {
        if(age<20){
            throw new MemberException(MemberErrorCode.UNDER_AGE);
        }
    }
}