package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingFilterDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("encontros")
public class MeetingController {

    private MeetingService meetingService;
    private MeetingMapper meetingMapper;

    MeetingController(MeetingService meetingService, MeetingMapper meetingMapper){
        this.meetingService = meetingService;
        this.meetingMapper = meetingMapper;
    }

    @GetMapping
    public ResponseEntity<List<MeetingResponseDto>> getMeetings(@ModelAttribute MeetingFilterDto filterDto){
        List<Meeting> meetings = meetingService.find(filterDto);
        List<MeetingResponseDto> meetingsDto = meetingMapper.meetingListToResponseDot(meetings);
        return ResponseEntity.ok(meetingsDto);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> createMeeting(@Valid @RequestBody MeetingCreateDto meetingCreateDto){
        meetingService.create(meetingCreateDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<MeetingResponseDto> updateMeeting(@Valid @RequestBody MeetingUpdateDto dto){
        Meeting meeting = meetingService.update(dto);
        MeetingResponseDto meetingResponse = meetingMapper.toResponseDto(meeting);
        return ResponseEntity.ok(meetingResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMeeting(Long id){
        meetingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
