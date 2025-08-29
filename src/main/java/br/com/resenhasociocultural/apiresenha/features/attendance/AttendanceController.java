package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceFilterDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/presenca")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final AttendanceMapper attendanceMapper;

    AttendanceController(AttendanceService attendanceService, AttendanceMapper attendanceMapper){
        this.attendanceService = attendanceService;
        this.attendanceMapper = attendanceMapper;
    }

    @GetMapping
    public ResponseEntity<Set<AttendanceResponseDto>> find(@RequestParam AttendanceFilterDto dto)
    {
        Set<Attendance> attendances = attendanceService.findByFilter(dto);
        Set<AttendanceResponseDto> response = attendanceMapper.toResponseListDto(attendances);
        return ResponseEntity.ok(response);
    }
}
