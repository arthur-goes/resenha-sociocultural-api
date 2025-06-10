package br.com.resenhasociocultural.apiresenha.dto;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        LocalDateTime timestamp,
        int status,
        String reasonPhrase,
        String message,
        String path
) {
}
