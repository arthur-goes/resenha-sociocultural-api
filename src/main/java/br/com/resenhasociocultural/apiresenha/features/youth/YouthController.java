package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jovens")
public class YouthController {


    private final YouthService youthService;
    private final YouthMapper youthMapper;

    public YouthController(YouthService youthService, YouthMapper youthMapper) {
        this.youthService = youthService;
        this.youthMapper = youthMapper;
    }

    @GetMapping
    public ResponseEntity<List<? extends YouthView>> findYouths(
            @RequestParam(name = "view", defaultValue = "COMPLETE", required = false) YouthViewType view,
            @RequestParam(name = "nome", defaultValue = "", required = false) String name
    ){
        List<Youth> youthList = name.isBlank() ? youthService.findAll() : youthService.findByName(name);

        return switch (view) {
            case SIMPLE -> ResponseEntity.ok(youthMapper.toSimpleResponseDtoList(youthList));
            case COMPLETE -> ResponseEntity.ok(youthMapper.toResponseDtoList(youthList));
        };
    }

    @GetMapping("{id}")
    public ResponseEntity<YouthResponseDto> findYouthById(@PathVariable("id") Long id){
        Youth youth = youthService.findById(id);
        YouthResponseDto youthResponseDto = youthMapper.toResponseDto(youth);
        return ResponseEntity.ok(youthResponseDto);
    }

    @PostMapping
    public ResponseEntity<YouthResponseDto> addYouth(@RequestBody @Valid YouthCreateDto youthCreateDto){
        Youth youthToSave = youthMapper.toEntity(youthCreateDto);
        Youth savedYouth = youthService.save(youthToSave);

        YouthResponseDto youthResponseDto = youthMapper.toResponseDto(savedYouth);
        return ResponseEntity.status(HttpStatus.CREATED).body(youthResponseDto);
    }

    @PatchMapping("{id}/useradmin")
    public ResponseEntity<YouthResponseDto> updateYouthByAdmin(@RequestBody YouthUpdateDto updatedDataDto){
        Youth youthResponse = youthService.update(updatedDataDto);
        YouthResponseDto youthResponseDto = youthMapper.toResponseDto(youthResponse);
        return ResponseEntity.ok(youthResponseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteYouthById(@PathVariable Long id){
        youthService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
