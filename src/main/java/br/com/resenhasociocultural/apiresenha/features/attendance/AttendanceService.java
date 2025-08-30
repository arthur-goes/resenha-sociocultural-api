package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceFilterDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final YouthService youthService;
    private final AttendanceSpecs attendanceSpecs;

    public AttendanceService(AttendanceRepository attendanceRepository, YouthService youthService, AttendanceMapper attendanceMapper, AttendanceSpecs attendanceSpecs) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
        this.youthService = youthService;
        this.attendanceSpecs = attendanceSpecs;
    }

    public Set<Attendance> findByFilter(AttendanceFilterDto filters){
        System.out.println("Filtro recebido: " + filters);
        validateDateFilters(filters);
        Specification<Attendance> specs = buildSpecificationsFromFilters(filters);

        return attendanceRepository.findAllAsSet(specs);
    }

    private void validateDateFilters(AttendanceFilterDto filters){
        boolean areDateParamsConflicting = filters.date() != null && (filters.initialDate() !=null || filters.finalDate() != null);
        boolean isDateBetweenInconsitent = (filters.initialDate() == null && filters.finalDate() != null) || (filters.initialDate() != null && filters.finalDate() == null);

        if (areDateParamsConflicting) {
            throw new IllegalArgumentException("Inconsistência nos parâmetros de data enviado. A filtragem por data deve ser para uma data específica ou um intervalo, não para os dois simultâneamente.");
        }

        if (isDateBetweenInconsitent){
            throw new IllegalArgumentException("Inconsistência nos parâmetros de data enviado. A filtragem por intervalo de data deve obrigatoriamente ter uma data inicial e uma data final.");
        }
    }

    private Specification<Attendance> buildSpecificationsFromFilters(AttendanceFilterDto filters){
        boolean isDateBetweenApplied = filters.initialDate() != null && filters.finalDate() != null;
        boolean isDateBetweenIntervalNotInverted = isDateBetweenApplied && (filters.initialDate().isBefore(filters.finalDate()));

        Specification<Attendance> specifications = (root, query, cb) -> cb.conjunction();

        if (filters.youthName() != null){
            specifications = specifications.and(attendanceSpecs.youthNameOrSurnameLike(filters.youthName()));
        }

        if (filters.date() != null){
            specifications = specifications.and(attendanceSpecs.dateEqual(filters.date()));
        }

        if (!isDateBetweenApplied){
            return specifications;
        }

        if (isDateBetweenIntervalNotInverted) {
            return specifications = specifications.and(attendanceSpecs.dateBetween(filters.initialDate(), filters.finalDate()));
        }
        return specifications.and(attendanceSpecs.dateBetween(filters.initialDate(), filters.initialDate()));

    }

    public Attendance findById(Long id){
        return attendanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível localizar uma presença de id " + id));
    }

    public Attendance create(AttendanceCreateDto dto){
        Attendance attendance = attendanceMapper.toEntity(dto, youthService);
        return attendanceRepository.save(attendance);
    }

}
