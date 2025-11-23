package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingFilterDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingUpdateDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/meetings")
public class MeetingController {

    private final MeetingService meetingService;
    private final MeetingMapper meetingMapper;

    @GetMapping
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<List<MeetingResponseDto>> getMeetings(@ModelAttribute MeetingFilterDto filterDto){
        List<Meeting> meetings = meetingService.findWithFilters(filterDto);
        List<MeetingResponseDto> meetingsDto = meetingMapper.toResponseDtoList(meetings);
        return ResponseEntity.ok(meetingsDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<MeetingResponseDto> findMeeting(@PathVariable Long id){
        Meeting foundMeeting = meetingService.findById(id);
        MeetingResponseDto responseDto = meetingMapper.toResponseDto(foundMeeting);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<MeetingResponseDto> createMeeting(@Valid @RequestBody MeetingCreateDto meetingDto){
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
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<MeetingResponseDto> updateMeeting(@PathVariable(name = "id") Long id, @Valid @RequestBody MeetingUpdateDto dto){
        Meeting meeting = meetingService.update(id, dto);
        MeetingResponseDto meetingResponse = meetingMapper.toResponseDto(meeting);
        return ResponseEntity.ok(meetingResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<Void> deleteMeeting(@PathVariable(name = "id") Long id){
        meetingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
