package xyz.moveuk.javasecurity.api.auth.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.moveuk.javasecurity.api.auth.security.UserPrincipal;

import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtPlugin jwtPlugin;

    private static final Pattern BEARER_PATTERN = Pattern.compile("^Bearer (.+?)$");

    public JwtAuthenticationFilter(JwtPlugin jwtPlugin) {
        this.jwtPlugin = jwtPlugin;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String jwt = getBearerToken(request);

        if (jwt != null) {
                Jws<Claims> token = jwtPlugin.validateToken(jwt);
                Long userId = Long.parseLong(token.getPayload().getSubject());
                String role = token.getPayload().get("role", String.class);

                UserPrincipal principal = new UserPrincipal(userId, Set.of(role).stream().map(eachRole -> new SimpleGrantedAuthority("Role_" + eachRole)).collect(Collectors.toSet()));
                JwtAuthenticationToken authentication = new JwtAuthenticationToken(
                        principal,
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getBearerToken(HttpServletRequest request) {
        String headerValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (headerValue == null) {
            return null;
        }
        Matcher matcher = BEARER_PATTERN.matcher(headerValue);
        return matcher.find() ? matcher.group(1) : null;
    }
}