package br.com.resenhasociocultural.apiresenha.dto.participationpoint;

public record ParticipationPointCreateDto(
    Long youthId,
    int amount,
    String reason,
    Long meetingId,
    boolean active
) {
}
