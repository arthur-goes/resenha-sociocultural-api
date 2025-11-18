package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceFilterDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final AttendanceMapper attendanceMapper;

    @GetMapping
    public ResponseEntity<List<AttendanceResponseDto>> find(@Valid @ModelAttribute AttendanceFilterDto dto) {
        List<AttendanceEntry> attendances = attendanceService.findWithFilters(dto);
        List<AttendanceResponseDto> response = attendanceMapper.toResponseListDto(attendances);
        return ResponseEntity.ok(response);
    }
}
