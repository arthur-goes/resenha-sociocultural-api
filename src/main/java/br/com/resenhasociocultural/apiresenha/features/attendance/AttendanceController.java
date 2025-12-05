package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultura.apiresenha.api.controller.AttendancesApi;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceFilter;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/attendances")
public class AttendanceController implements AttendancesApi {

    private final AttendanceService attendanceService;
    private final AttendanceMapper attendanceMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'COORDINATOR', 'ADMIN')")
    public ResponseEntity<List<AttendanceResponse>> findAttendances(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "date") LocalDate date,
        @RequestParam(name = "initialDate") LocalDate initialDate,
        @RequestParam(name = "finalDate") LocalDate finalDate
        ) {
        AttendanceFilter dto = new AttendanceFilter(name, date, initialDate, finalDate);

        List<Attendance> attendances = attendanceService.findWithFilters(dto);
        List<AttendanceResponse> response = attendanceMapper.toResponseListDto(attendances);
        return ResponseEntity.ok(response);
    }
}
