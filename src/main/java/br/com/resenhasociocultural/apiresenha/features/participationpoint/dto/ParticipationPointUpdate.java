package br.com.resenhasociocultural.apiresenha.features.participationpoint.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSummary;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParticipationPointUpdate(
  @NotNull
  Long id,
  YouthSummary youth,
  @NotNull
  int amount,
  @NotBlank
  String reason,
  @NotNull
  boolean active
) {
}
