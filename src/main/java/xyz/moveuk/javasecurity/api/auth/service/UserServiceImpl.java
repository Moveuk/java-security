package xyz.moveuk.javasecurity.api.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.moveuk.javasecurity.api.auth.model.SignUpRequest;
import xyz.moveuk.javasecurity.api.auth.model.SignUpResponse;
import xyz.moveuk.javasecurity.database.entity.User;
import xyz.moveuk.javasecurity.database.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalStateException("이메일이 이미 존재합니다.");
        }

        User savedUser = userRepository.save(User.builder()
                .username(request.username())
                .password(bCryptPasswordEncoder.encode(request.password()))
                .nickname(request.nickname())
                .authority("ROLE_USER")
                .createdAt(LocalDateTime.now())
                .build());

        return SignUpResponse.of(savedUser);
    }
}
