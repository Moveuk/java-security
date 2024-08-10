package xyz.moveuk.javasecurity.api.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import xyz.moveuk.javasecurity.database.entity.User;

import java.util.Collections;
import java.util.List;

public record SignUpResponse(
        @Schema(description = "이름")
        String username,
        @Schema(description = "닉네임")
        String nickname,
        @Schema(description = "권한")
        List<Authority> authorities
) {
    public record Authority(String authorityName) {}
    public static SignUpResponse of(User entity) {
        return new SignUpResponse(
                entity.getUsername(),
                entity.getNickname(),
                Collections.singletonList(new Authority(entity.getAuthority().name()))
        );
    }
}
