package xyz.moveuk.javasecurity.api.auth.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import xyz.moveuk.javasecurity.api.auth.security.UserPrincipal;

import java.io.Serializable;

public class JwtAuthenticationToken extends AbstractAuthenticationToken implements Serializable {

    private final UserPrincipal principal;

    public JwtAuthenticationToken(UserPrincipal principal, WebAuthenticationDetails details) {
        super(principal.authorities());
        this.principal = principal;
        super.setAuthenticated(true);
        super.setDetails(details);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}