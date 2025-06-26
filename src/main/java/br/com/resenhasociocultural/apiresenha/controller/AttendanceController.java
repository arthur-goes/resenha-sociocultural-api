package br.com.resenhasociocultural.apiresenha.controller;

import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.mapper.AttendanceMapper;
import br.com.resenhasociocultural.apiresenha.model.Attendance;
import br.com.resenhasociocultural.apiresenha.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
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
    public ResponseEntity<Set<AttendanceResponseDto>> find(
            @RequestParam(name = "nome", required = false) String youthName,
            @RequestParam(name = "data", required = false) LocalDate date,
            @RequestParam(name = "dataInicial", required = false) LocalDate firstDate,
            @RequestParam(name = "dataFinal", required = false) LocalDate finalDate
    )
    {
        Set<Attendance> attendances = attendanceService.find(youthName, date, firstDate, finalDate);
        Set<AttendanceResponseDto> response = attendanceMapper.toAttendanceResponseListDto(attendances);
        return ResponseEntity.ok(response);
    }
}
