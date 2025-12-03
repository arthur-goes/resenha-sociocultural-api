package br.com.resenhasociocultural.apiresenha.features.participationpoint.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParticipationPointUpdateDto(
  @NotNull
  Long id,
  YouthSimpleDto youth,
  @NotNull
  int amount,
  @NotBlank
  String reason,
  @NotNull
  boolean active
) {
}
