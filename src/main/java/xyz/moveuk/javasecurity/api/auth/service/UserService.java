package xyz.moveuk.javasecurity.api.auth.service;

import xyz.moveuk.javasecurity.api.auth.model.SignUpRequest;
import xyz.moveuk.javasecurity.api.auth.model.SignUpResponse;

public interface UserService {
    SignUpResponse signUp(SignUpRequest request);
    //TODO LoginResponse login(LoginRequest request);
}
