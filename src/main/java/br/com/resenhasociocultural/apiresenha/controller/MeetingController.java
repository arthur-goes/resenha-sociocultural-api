package br.com.resenhasociocultural.apiresenha.controller;

import br.com.resenhasociocultural.apiresenha.dto.meeting.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.meeting.MeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.mapper.MeetingMapper;
import br.com.resenhasociocultural.apiresenha.model.Meeting;
import br.com.resenhasociocultural.apiresenha.service.MeetingService;
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
    public ResponseEntity<List<MeetingResponseDto>> getMeetings(){
        List<Meeting> meetings = meetingService.findAll();
        List<MeetingResponseDto> meetingsDto = meetingMapper.meetingListToResponseDot(meetings);
        return ResponseEntity.ok(meetingsDto);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> createMeeting(@Valid @RequestBody MeetingCreateDto meetingCreateDto){
        meetingService.create(meetingCreateDto);
        return ResponseEntity.noContent().build();
    }
}
