package xyz.moveuk.javasecurity.api.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.moveuk.javasecurity.api.auth.model.LoginRequest;
import xyz.moveuk.javasecurity.api.auth.model.LoginResponse;
import xyz.moveuk.javasecurity.api.auth.model.SignUpRequest;
import xyz.moveuk.javasecurity.api.auth.model.SignUpResponse;
import xyz.moveuk.javasecurity.api.auth.security.jwt.JwtPlugin;
import xyz.moveuk.javasecurity.database.entity.User;
import xyz.moveuk.javasecurity.database.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtPlugin jwtPlugin;

    @Override
    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("이메일이 이미 존재합니다.");
        }

        User newUser = User.builder()
                .username(request.username())
                .password(bCryptPasswordEncoder.encode(request.password()))
                .nickname(request.nickname())
                .authority("ROLE_USER")
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(newUser);

        return SignUpResponse.of(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

        Optional<User> optionalUser = userRepository.findByUsername(request.username());


        if (optionalUser.isEmpty()
                || (!optionalUser.get().getUsername().equals(request.username()) || !bCryptPasswordEncoder.matches(request.password(), optionalUser.get().getPassword()))) {
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 잘못되었습니다.");
        }

        return new LoginResponse(
                jwtPlugin.generateAccessToken(
                        optionalUser.get().getId().toString(),
                        optionalUser.get().getAuthority().name()
                )
        );
    }
}
