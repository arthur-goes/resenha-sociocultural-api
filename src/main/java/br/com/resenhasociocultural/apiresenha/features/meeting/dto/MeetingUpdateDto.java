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

    private final Set<AttendanceCreateDto> attendanceList;
    private final Set<StrikeCreateDto> strikes;
    private final Set<ParticipationPointCreateDto> participationPoints;

    @JsonCreator
    public MeetingUpdateDto(
        @JsonProperty("date") LocalDate date,
        @JsonProperty("theme") String theme,
        @JsonProperty("minutosDeSabedoriaLesson") String minutosDeSabedoriaLesson,
        @JsonProperty("attendanceList") Set<AttendanceCreateDto> attendanceList,
        @JsonProperty("strikes") Set<StrikeCreateDto> strikes,
        @JsonProperty("participationPoints") Set<ParticipationPointCreateDto> participationPoints
    ) {
        this.date = date;
        this.theme = theme;
        this.minutosDeSabedoriaLesson = minutosDeSabedoriaLesson;
        this.attendanceList = attendanceList;
        this.strikes = strikes;
        this.participationPoints = participationPoints;
    }

    public void setId(Long id) {
        if (this.id == null){
            this.id = id;
        }
    }

    @Override
    public String toString() {
        return "[date: " + this.date + ", theme: " + this.theme + ", mds: " + this.minutosDeSabedoriaLesson + ", attendanceList: " + this.attendanceList + "]";
    }
}
