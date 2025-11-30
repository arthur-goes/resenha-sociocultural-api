package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.exception.DateConflictArgumentException;
import br.com.resenhasociocultural.apiresenha.exception.InconsistentDateIntervalArgumentException;
import br.com.resenhasociocultural.apiresenha.exception.MalformedMeetingException;
import br.com.resenhasociocultural.apiresenha.features.attendance.Attendance;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.EntryValidationDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingFilterDto;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.features.strike.Strike;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthEntry;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MeetingValidator {

    private YouthService youthService;

    private static final Map<Class<? extends YouthEntry>, String> YOUTH_ENTRY_DESCRIPTIONS;

    static {
        YOUTH_ENTRY_DESCRIPTIONS = Map.of(
            Attendance.class, "uma Presença",
            Strike.class, "um Strike",
            ParticipationPoint.class, "um Ponto de Participação"
        );
    }

    public void validateFilters(MeetingFilterDto filters) {

        LocalDate initialDate = filters.initialDate();
        LocalDate finalDate = filters.finalDate();
        LocalDate date = filters.date();

        boolean isSingleDateFilterApplied = date != null;
        boolean isDateIntervalFilterApplied = initialDate != null && finalDate != null;

        boolean isInitialDateDefined = initialDate != null;
        boolean isFinalDateDefined = finalDate != null;

        boolean dateIntervalIsIncomplete = isInitialDateDefined != isFinalDateDefined;

        if (dateIntervalIsIncomplete) {
            throw new InconsistentDateIntervalArgumentException();
        }

        if (isSingleDateFilterApplied && isDateIntervalFilterApplied) {
            throw new DateConflictArgumentException();
        }

        if (isDateIntervalFilterApplied && finalDate.isBefore(initialDate)) {
            throw new InconsistentDateIntervalArgumentException();
        }

    }

    public void validateYouthEntries(Meeting meeting) {
        List<EntryValidationDto> entriesToValidate = new ArrayList<>();

        addEntriesToBeValidated(entriesToValidate, meeting.getAttendances());
        addEntriesToBeValidated(entriesToValidate, meeting.getStrikes());
        addEntriesToBeValidated(entriesToValidate, meeting.getParticipationPoints());

        if (entriesToValidate.isEmpty()) {
            return;
        }

        Set<Long> idsToValidate = entriesToValidate.stream()
            .map(EntryValidationDto::youthId)
            .collect(Collectors.toSet());

        Set<Long> foundIds = youthService.findValidYouthIdsIn(idsToValidate);
        List<EntryValidationDto> invalidEntries = entriesToValidate.stream()
            .filter(entryToValidate -> !foundIds.contains(entryToValidate.youthId()))
            .toList();

        if (invalidEntries.isEmpty()) {
            return;
        }

        StringBuilder errorMessage = new StringBuilder("Não foi possível prosseguir com a solicitação. Os seguintes erros foram encontrados:");
        for (EntryValidationDto youthEntry : invalidEntries) {
            errorMessage.append(
                String.format("\n- O jovem %s com id %d vinculado a %s não foi encontrado no banco de dados",
                    youthEntry.fullName(),
                    youthEntry.youthId(),
                    youthEntry.originEntry())
            );
        }
        throw new MalformedMeetingException(errorMessage.toString());
    }

    private void addEntriesToBeValidated(List<EntryValidationDto> entriesToValidate, Set<? extends YouthEntry> entries) {
        if (entries == null || entries.isEmpty()) {
            return;
        }

        entries.forEach(entry -> {
            String originDescription = YOUTH_ENTRY_DESCRIPTIONS.get(entry.getClass());

            if (originDescription == null) {
                throw new IllegalStateException(
                    String.format(
                        "Configuração de descrição para uma coleção do tipo YouthEntry não encontrada para a classe %s. Adicione uma configuração de entrada ao mapa estático de configuração em MeetingValidator.",
                        entry.getClass()
                    )
                );
            }

            EntryValidationDto validationDto = new EntryValidationDto(
                entry.getYouth().getId(),
                entry.getYouth().getFullName(),
                originDescription
            );
            entriesToValidate.add(validationDto);
        });
    }
}