package br.com.resenhasociocultural.apiresenha.features.meeting.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointCreateDto;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeCreateDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Getter
public class MeetingUpdateDto{
    private Long id;

    @PastOrPresent(message = "A data do encontro não pode ser futura")
    @NotNull(message = "A data precisa ser preenchida")
    private final LocalDate date;

    @NotBlank(message = "O Tema precisa ser fornecido")
    private final String theme;

    private final String minutosDeSabedoriaLesson;

    private final Set<AttendanceCreateDto> attendanceEntries;
    private final Set<StrikeCreateDto> strikeEntries;
    private final Set<ParticipationPointCreateDto> participationPointEntries;

    @JsonCreator
    public MeetingUpdateDto(
        @JsonProperty("date") LocalDate date,
        @JsonProperty("theme") String theme,
        @JsonProperty("minutosDeSabedoriaLesson") String minutosDeSabedoriaLesson,
        @JsonProperty("attendanceEntries") Set<AttendanceCreateDto> attendanceEntries,
        @JsonProperty("strikeEntries") Set<StrikeCreateDto> strikeEntries,
        @JsonProperty("participationPointEntries") Set<ParticipationPointCreateDto> participationPointEntries
    ) {
        this.date = date;
        this.theme = theme;
        this.minutosDeSabedoriaLesson = minutosDeSabedoriaLesson;
        this.attendanceEntries = attendanceEntries;
        this.strikeEntries = strikeEntries;
        this.participationPointEntries = participationPointEntries;
    }

    public void setId(Long id) {
        if (this.id == null){
            this.id = id;
        }
    }
}
