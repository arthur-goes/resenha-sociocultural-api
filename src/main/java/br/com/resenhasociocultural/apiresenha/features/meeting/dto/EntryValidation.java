package br.com.resenhasociocultural.apiresenha.features.meeting.dto;

import java.time.LocalDate;

public record EntryValidation(
    Long youthId,
    String fullName,
    LocalDate deactivationDate,
    String originEntry
) {
}
