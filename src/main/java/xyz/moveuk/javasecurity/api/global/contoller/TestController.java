package xyz.moveuk.javasecurity.api.global.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/need")
@RequiredArgsConstructor
public class TestController {

    @PostMapping("/token")
    @Operation(summary = "토큰 유무에 따른 error 메세지 변경 체크용 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "filter에서 정상적으로 token이 체크됨"),
            @ApiResponse(responseCode = "401", description = "사용자가 인증되지 않은 상태에서 보호된 리소스에 접근하려고 할 때 호출"),
            @ApiResponse(responseCode = "403", description = "사용자가 인증은 되었지만, 요청한 리소스에 대한 권한이 없을 때 호출")
    })
    public ResponseEntity<String> checkToken() {
        return ResponseEntity.ok("token이 존재합니다!");
    }
}
