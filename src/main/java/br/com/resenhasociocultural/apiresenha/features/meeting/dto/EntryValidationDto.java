package br.com.resenhasociocultural.apiresenha.features.meeting.dto;

public record EntryValidationDto(
    Long youthId,
    String fullName,
    String originEntry
) {
}
