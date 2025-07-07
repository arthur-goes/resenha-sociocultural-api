package br.com.resenhasociocultural.apiresenha.dto.strike;

import br.com.resenhasociocultural.apiresenha.dto.youth.YouthSimpleDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StrikeCreateDto(
    Long meetingId,
    @NotNull
    Long youthId,
    @NotBlank
    int amount,
    String reason,
    @NotNull
    boolean active
) {
}
