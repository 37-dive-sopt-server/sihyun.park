package org.sopt.global.exception;

import org.sopt.global.constant.ArticleErrorCode;

public class ArticleException extends GlobalException {
    public ArticleException(ArticleErrorCode errorCode) {
        super(errorCode);
    }
}
