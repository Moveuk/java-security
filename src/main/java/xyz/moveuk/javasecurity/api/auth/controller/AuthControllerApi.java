package xyz.moveuk.javasecurity.api.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.moveuk.javasecurity.api.auth.model.LoginRequest;
import xyz.moveuk.javasecurity.api.auth.model.LoginResponse;
import xyz.moveuk.javasecurity.api.auth.model.SignUpRequest;
import xyz.moveuk.javasecurity.api.auth.model.SignUpResponse;

public interface AuthControllerApi {

    @Operation(summary = "사용자 회원가입", description = "새 사용자를 등록하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "사용자 회원가입 성공")
    })
    ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid SignUpRequest request);

    @Operation(summary = "사용자 로그인", description = "사용자가 로그인할 수 있는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "사용자가 잘못된 로그인 시도를 할 경우 호출")
    })
    ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request);
}