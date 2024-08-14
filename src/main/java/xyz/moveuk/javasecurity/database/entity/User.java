package xyz.moveuk.javasecurity.database.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private Authority authority;
    private LocalDateTime createdAt;

    @Builder
    public User(String username, String password, String nickname, String authority, LocalDateTime createdAt) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.authority = Authority.valueOf(authority);
        this.createdAt = createdAt;
    }

    public enum Authority {
        ROLE_USER,
        ROLE_ADMIN
    }
}
