package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceFilterDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
import org.assertj.core.api.ThrowableTypeAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttendanceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private AttendanceMapper attendanceMapper;

    @Mock
    private YouthService youthService;

    @Mock
    private AttendanceSpecs attendanceSpecs;

    @InjectMocks
    AttendanceService attendanceService;

    @Test
    public void givenSingleDateAndDateInterval_whenValidatingFilter_thenThrowsIllegalArgumentException(){
        AttendanceFilterDto filters = new AttendanceFilterDto(
            null,
            LocalDate.now(),
            LocalDate.now().minusDays(1),
            LocalDate.now()
        );
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> attendanceService.findByFilter(filters))
            .withMessage("Inconsistência nos parâmetros de data enviado. A filtragem por data deve ser para uma data específica ou um intervalo, não para os dois simultâneamente.");

        verify(attendanceRepository, never()).findAllAsSet(any(Specification.class));
    }

    @Test
    public void givenIncompleteDateInterval_whenValidatingFilter_thenThrowsIllegalArgumnetException(){
        AttendanceFilterDto filter1 = new AttendanceFilterDto(
            null,
            null,
            LocalDate.of(2025,8,20),
            null
        );

        AttendanceFilterDto filter2 = new AttendanceFilterDto(
            null,
            null,
            null,
            LocalDate.of(2025,8,20)
        );

        String message = "Inconsistência nos parâmetros de data enviado. A filtragem por intervalo de data deve obrigatoriamente ter uma data inicial e uma data final."

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> attendanceService.findByFilter(filter1))
            .withMessage(message);

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> attendanceService.findByFilter(filter2))
            .withMessage(message);

        verify(attendanceRepository, never()).findAllAsSet(any(Specification.class));
    }

    @Test
    public void whenFindByFilter_withName_thenNameSpecificationIsCalled(){
        AttendanceFilterDto filters = new AttendanceFilterDto(
            "Youthname",
            null,
            null,
            null
        );

        attendanceService.findByFilter(filters);

        verify(attendanceSpecs, times(1)).youthNameOrSurnameLike(filters.youthName());

        verify(attendanceSpecs, never()).dateEqual(any());
        verify(attendanceSpecs, never()).dateBetween(any(), any());

        verify(attendanceRepository, times(1)).findAllAsSet(any(Specification.class));
    }

    @Test
    public void whenFindByFilter_withNameAndDate_thenNameAndDateSpecificationsAreCalled(){
        AttendanceFilterDto filters = new AttendanceFilterDto(
            "Youthname",
            LocalDate.now(),
            null,
            null
        );

        attendanceService.findByFilter(filters);

        verify(attendanceSpecs, times(1)).youthNameOrSurnameLike(filters.youthName());
        verify(attendanceSpecs, times(1)).dateEqual(filters.date());

        verify(attendanceSpecs, never()).dateBetween(any(LocalDate.class), any(LocalDate.class));

        verify(attendanceRepository, times(1)).findAllAsSet(any(Specification.class));
    }

    @Test
    public void whenFindByFilter_withDateInterval_thenDateIntervallSpecificationIsCalled(){
        AttendanceFilterDto filters = new AttendanceFilterDto(
            null,
            null,
            LocalDate.now().minusDays(1),
            LocalDate.now()
        );

        attendanceService.findByFilter(filters);

        verify(attendanceSpecs, times(1)).dateBetween(filters.initialDate(), filters.finalDate());

        verify(attendanceSpecs, never()).youthNameOrSurnameLike(any());
        verify(attendanceSpecs, never()).dateEqual(any());

        verify(attendanceRepository, times(1)).findAllAsSet(any(Specification.class));
    }

    @Test
    public void whenFindByFilter_withInvertedDateInterval_thenDateIntervallSpecificationIsCalled(){
        AttendanceFilterDto filters = new AttendanceFilterDto(
            null,
            null,
            LocalDate.now(),
            LocalDate.now().minusDays(1)
        );

        attendanceService.findByFilter(filters);

        verify(attendanceSpecs, times(1)).dateBetween(filters.finalDate(), filters.initialDate());

        verify(attendanceSpecs, never()).youthNameOrSurnameLike(any());
        verify(attendanceSpecs, never()).dateEqual(any());

        verify(attendanceRepository, times(1)).findAllAsSet(any(Specification.class));
    }
}
