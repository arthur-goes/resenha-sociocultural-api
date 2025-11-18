package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceFilterDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final AttendanceSpecs attendanceSpecs;

    public List<AttendanceEntry> findWithFilters(AttendanceFilterDto filters){
        validateDateFilters(filters);
        Specification<AttendanceEntry> specs = attendanceSpecs.buildSpecificationsFromFilters(filters);

        Sort youthSortByName = Sort.by(
            Sort.Order.asc("youth.first_name"),
            Sort.Order.asc("youth.surname")
        );

        return attendanceRepository.findAll(specs, youthSortByName);
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


    public AttendanceEntry findById(Long id){
        return attendanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível localizar uma presença de id " + id));
    }

    public AttendanceEntry create(AttendanceCreateDto dto){
        AttendanceEntry attendance = attendanceMapper.toEntity(dto);
        return attendanceRepository.save(attendance);
    }

}
