package xyz.moveuk.javasecurity.api.auth.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UserPrincipal(
        Long id,
        Collection<GrantedAuthority> authorities
) {
}
