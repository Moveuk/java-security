package xyz.moveuk.javasecurity.api.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @Schema(description = "이름")
        @Size(min = 1, max = 30)
        @NotBlank
        String username,

        @Schema(description = "비밀번호")
        @Size(min = 8, max = 64)
        @NotBlank
        String password,

        @Schema(description = "닉네임")
        @Size(min = 2, max = 16)
        @NotBlank
        String nickname
) {
}
