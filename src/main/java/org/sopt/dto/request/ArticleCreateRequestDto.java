package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.sopt.domain.Tag;

public record ArticleCreateRequestDto(
        @NotNull
        Long memberId,
        @NotNull
        Tag tag,
        @NotBlank(message = "제목 입력은 필수입니다.")
        String title,
        @NotBlank(message = "내용 입력은 필수입니다.")
        String content
) {}
