package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static br.com.resenhasociocultural.apiresenha.features.attendance.builder.AttendanceBuilder.anAttendance;
import static br.com.resenhasociocultural.apiresenha.features.attendance.builder.AttendanceFilterDtoBuilder.anAttendanceFilterDto;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttendanceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private AttendanceSpecs attendanceSpecs;

    @InjectMocks
    AttendanceService attendanceService;

    @ParameterizedTest(name = "Cenario: {0}")
    @MethodSource("invalidFilterScenarios")
    public void givenInvalidFilter_whenFindWithFilters_thenThrowException(String description, AttendanceFilter filterDto, String errorMessage){

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> attendanceService.findWithFilters(filterDto))
            .withMessage(errorMessage);

        verify(attendanceRepository, never()).findAll(any(Specification.class), any(Sort.class));
        verify(attendanceSpecs, never()).buildSpecificationsFromFilters(filterDto);
    }

    private static Stream<Arguments> invalidFilterScenarios() {
        AttendanceFilter onlyInitialDateFilter = anAttendanceFilterDto()
          .withYouthNameSubstring(null)
          .withDate(null)
          .withInitialDate(LocalDate.of(2025, 8, 20))
          .withFinalDate(null)
          .build();

        AttendanceFilter onlyFinalDateFilter = anAttendanceFilterDto()
          .withYouthNameSubstring(null)
          .withDate(null)
          .withInitialDate(null)
          .withFinalDate(LocalDate.of(2025, 8, 20))
          .build();

        AttendanceFilter allDateParamsFilledFilter = anAttendanceFilterDto()
          .withYouthNameSubstring(null)
          .withDate(LocalDate.of(2025, 5, 5))
          .withInitialDate(LocalDate.of(2025, 8, 19))
          .withFinalDate(LocalDate.of(2025, 8, 21))
          .build();

        String dateInconsistentMessage = "Inconsistência nos parâmetros de data enviado. A filtragem por intervalo de " +
          "data deve obrigatoriamente ter uma data inicial e uma data final.";

        String dateConflictMessage = "Inconsistência nos parâmetros de data enviado. " +
          "A filtragem por data deve ser para uma data específica ou um intervalo, não para os dois simultâneamente.";

        return Stream.of(
          Arguments.of("Invalid filter. Missing final date, only initial date is given.", onlyInitialDateFilter, dateInconsistentMessage),
          Arguments.of("Invalid filter. Missing initial date, only final date is given", onlyFinalDateFilter, dateInconsistentMessage),
          Arguments.of("Invalid filter. All date params are given.", allDateParamsFilledFilter, dateConflictMessage)
        );
    }
    @ParameterizedTest(name = "Cenario: {0}")
    @MethodSource("validFilterScenarios")
    public void givenValidFilter_whenFindWithFilters_thenFindAllAttendances(String description, AttendanceFilter filterDto){
        Specification<Attendance> specs = (root, query, cb) -> cb.conjunction();
        ArgumentCaptor<Sort> sortCaptor = ArgumentCaptor.forClass(Sort.class);

        when(attendanceSpecs.buildSpecificationsFromFilters(filterDto)).thenReturn(specs);

        attendanceService.findWithFilters(filterDto);

        verify(attendanceRepository, times(1)).findAll(eq(specs), sortCaptor.capture());

        Sort capturedSort = sortCaptor.getValue();

        assertThat(capturedSort.getOrderFor("youth.first_name")).isNotNull();
        assertThat(capturedSort.getOrderFor("youth.first_name").getDirection()).isEqualTo(Sort.Direction.ASC);

        assertThat(capturedSort.getOrderFor("youth.surname")).isNotNull();
        assertThat(capturedSort.getOrderFor("youth.surname").getDirection()).isEqualTo(Sort.Direction.ASC);

    }

    private static Stream<Arguments> validFilterScenarios() {
        AttendanceFilter nameFilter = anAttendanceFilterDto()
          .withYouthNameSubstring("ohn")
          .withDate(null)
          .withInitialDate(null)
          .withFinalDate(null)
          .build();

        AttendanceFilter singleDateFilter = anAttendanceFilterDto()
          .withYouthNameSubstring(null)
          .withDate(LocalDate.of(2025, 8, 5))
          .withInitialDate(null)
          .withFinalDate(null)
          .build();

        AttendanceFilter dateBetweenFilter = anAttendanceFilterDto()
          .withYouthNameSubstring(null)
          .withDate(null)
          .withInitialDate(LocalDate.of(2025, 8, 19))
          .withFinalDate(LocalDate.of(2025, 8, 21))
          .build();

        return Stream.of(
          Arguments.of("Valid filter. Youth name substring is given", nameFilter),
          Arguments.of("Valid filter. Single date is given", singleDateFilter),
          Arguments.of("Valid filter. Date interval is given", dateBetweenFilter)
        );
    }

    @Test
    public void givenInvalidId_whenFindById_thenThrowsException(){
        Long invalidId = -1L;
        when(attendanceRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> attendanceService.findById(invalidId));
    }

    @Test
    public void givenValidId_whenFindById_thenReturnEntity(){
        Long validId = 1L;
        Attendance attendance = anAttendance().build();
        when(attendanceRepository.findById(validId)).thenReturn(Optional.of(attendance));
        attendanceService.findById(validId);
        verify(attendanceRepository,times(1)).findById(validId);
    }
}
