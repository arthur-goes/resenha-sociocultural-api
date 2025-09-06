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
        List<Meeting> meetings = meetingService.findWithFilters(filterDto);
        List<MeetingResponseDto> meetingsDto = meetingMapper.meetingListToResponseDot(meetings);
        return ResponseEntity.ok(meetingsDto);
    }

    @GetMapping("/cadastro")
    public ResponseEntity<MeetingResponseDto> prepareNewMeeting(){
        Meeting meeting = meetingService.prepareNewMeetingData();
        MeetingResponseDto responseDto = meetingMapper.toResponseDto(meeting);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> createMeeting(@Valid @RequestBody MeetingCreateDto meetingDto){
        meetingService.create(meetingDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeetingResponseDto> updateMeeting(@PathVariable(name = "id") Long id, @Valid @RequestBody MeetingUpdateDto dto){
        dto.setId(id);
        Meeting meeting = meetingService.update(dto);
        MeetingResponseDto meetingResponse = meetingMapper.toResponseDto(meeting);
        return ResponseEntity.ok(meetingResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable(name = "id") Long id){
        meetingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
