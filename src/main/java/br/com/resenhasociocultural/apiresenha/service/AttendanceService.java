package br.com.resenhasociocultural.apiresenha.service;

import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.mapper.AttendanceMapper;
import br.com.resenhasociocultural.apiresenha.model.Attendance;
import br.com.resenhasociocultural.apiresenha.model.Youth;
import br.com.resenhasociocultural.apiresenha.repository.AttendanceRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static br.com.resenhasociocultural.apiresenha.repository.specs.AttendanceSpecs.*;

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
