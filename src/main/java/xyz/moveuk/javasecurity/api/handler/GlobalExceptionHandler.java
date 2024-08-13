package xyz.moveuk.javasecurity.api.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.moveuk.javasecurity.api.global.model.ErrorResponse;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        var message = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining());
        var errorResponse = new ErrorResponse(
                "ARGUMENT_NOT_VALID",
                message
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {
        var errorResponse = new ErrorResponse(
                "INTERNAL_SERVER_ERROR",
                "서버 에러입니다"
        );
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
