package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceSpecs.*;

import java.time.LocalDate;
import java.util.Set;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final YouthService youthService;

    public AttendanceService(AttendanceRepository attendanceRepository, YouthService youthService, AttendanceMapper attendanceMapper/*, MeetingService meetingService*/) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
        this.youthService = youthService;
    }

    public Set<Attendance> find(String youthName, LocalDate date, LocalDate initialDate, LocalDate finalDate){
        Specification<Attendance> specs = (root, query, cb) -> cb.conjunction();

        boolean areDateParamsConflicting = date != null && (initialDate !=null || finalDate != null);
        boolean isDateBetweenInconsitent = (initialDate == null && finalDate != null) || (initialDate != null && finalDate == null);
        boolean isDateBetweenApplied = initialDate != null && finalDate != null;
        boolean isDateBetweenIntervalNotInverted = isDateBetweenApplied && (initialDate.isBefore(finalDate));

        if (areDateParamsConflicting) {
            throw new IllegalArgumentException("Inconsistência nos parâmetros de data enviado. A filtragem por data deve ser para uma data específica ou um intervalo, não para os dois simultâneamente.");
        }

        if (isDateBetweenInconsitent){
            throw new IllegalArgumentException("Inconsistência nos parâmetros de data enviado. A filtragem por intervalo de data deve obrigatoriamente ter uma data inicial e uma data final.");
        }

        if (date != null){
            specs  = specs.and(dateEqual(date));
        } else if (isDateBetweenApplied) {
            if (isDateBetweenIntervalNotInverted) {
                specs = specs.and(dateBetween(initialDate, finalDate));
            } else {
                specs = specs.and(dateBetween(finalDate, initialDate));
            }
        }

        if (youthName != null){
            specs = specs.and(youthNameOrSurnameLike(youthName));
        }

        return attendanceRepository.findAllAsSet(specs);
    }

    public Attendance findById(Long id){
        return attendanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível localizar uma presença de id " + id));
    }

    public Attendance create(AttendanceCreateDto dto){
        Youth youth = null;
        if (dto.youthId() != null) {
            youth = youthService.findById(dto.youthId());
        }

        Attendance attendance = attendanceMapper.toEntity(dto);
        attendance.setYouth(youth);

        return attendanceRepository.save(attendance);
    }

}
