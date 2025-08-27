package br.com.resenhasociocultural.apiresenha.exception;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        LocalDateTime timestamp,
        int status,
        String reasonPhrase,
        String message,
        String path
) {
}
