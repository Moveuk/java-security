package xyz.moveuk.javasecurity.api.global.model;

public record ErrorResponse(
        String errorCode,
        String errorMessage
) {
}
