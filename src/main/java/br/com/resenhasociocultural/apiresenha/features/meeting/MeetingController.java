package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.api.controller.MeetingsApi;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingCreate;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingFilter;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingResponse;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingUpdate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/meetings")
public class MeetingController implements MeetingsApi {

    private final MeetingService meetingService;
    private final MeetingMapper meetingMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'COORDINATOR', 'ADMIN')")
    public ResponseEntity<List<MeetingResponse>> getMeetings(
        @RequestParam(name = "theme", required = false) String theme,
        @RequestParam(name = "date", required = false) LocalDate date,
        @RequestParam(name = "initialDate", required = false) LocalDate initialDate,
        @RequestParam(name = "finalDate", required = false) LocalDate finalDate
    ){
        MeetingFilter filter = new MeetingFilter(theme, initialDate, finalDate, date);
        List<Meeting> meetings = meetingService.findWithFilters(filter);
        List<MeetingResponse> meetingsDto = meetingMapper.toResponseDtoList(meetings);
        return ResponseEntity.ok(meetingsDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'COORDINATOR', 'ADMIN')")
    public ResponseEntity<MeetingResponse> findMeeting(@PathVariable Long id){
        Meeting foundMeeting = meetingService.findById(id);
        MeetingResponse responseDto = meetingMapper.toResponseDto(foundMeeting);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('COORDINATOR', 'ADMIN')")
    public ResponseEntity<MeetingResponse> createMeeting(@RequestBody MeetingCreate meetingDto){
        Meeting meeting = meetingService.create(meetingDto);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(meeting.getId())
            .toUri();
        var body = meetingMapper.toResponseDto(meeting);
        return ResponseEntity.created(location).body(body);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDINATOR', 'ADMIN')")
    public ResponseEntity<MeetingResponse> updateMeeting(@PathVariable(name = "id") Long id, @RequestBody MeetingUpdate dto){
        Meeting meeting = meetingService.update(id, dto);
        MeetingResponse meetingResponse = meetingMapper.toResponseDto(meeting);
        return ResponseEntity.ok(meetingResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMeeting(@PathVariable(name = "id") Long id){
        meetingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
