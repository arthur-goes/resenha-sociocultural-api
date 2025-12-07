package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.api.controller.AttendancesApi;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreate;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceFilter;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<Void> createAttendance(AttendanceCreate attendanceCreate) {
       return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'COORDINATOR', 'ADMIN')")
    public ResponseEntity<List<AttendanceResponse>> findAttendances(
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "date", required = false) LocalDate date,
        @RequestParam(name = "initialDate", required = false) LocalDate initialDate,
        @RequestParam(name = "finalDate", required = false) LocalDate finalDate
        ) {
        AttendanceFilter dto = new AttendanceFilter(name, date, initialDate, finalDate);

        List<Attendance> attendances = attendanceService.findWithFilters(dto);
        List<AttendanceResponse> response = attendanceMapper.toResponseListDto(attendances);
        return ResponseEntity.ok(response);
    }
}
