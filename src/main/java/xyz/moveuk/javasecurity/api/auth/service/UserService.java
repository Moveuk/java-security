package xyz.moveuk.javasecurity.api.auth.service;

import xyz.moveuk.javasecurity.api.auth.model.LoginRequest;
import xyz.moveuk.javasecurity.api.auth.model.LoginResponse;
import xyz.moveuk.javasecurity.api.auth.model.SignUpRequest;
import xyz.moveuk.javasecurity.api.auth.model.SignUpResponse;

public interface UserService {
    SignUpResponse signUp(SignUpRequest request);
    LoginResponse login(LoginRequest request);
}
