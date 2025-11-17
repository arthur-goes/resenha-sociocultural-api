package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/jovens")
public class YouthController {

    private final YouthService youthService;
    private final YouthMapper youthMapper;

    @GetMapping
    public ResponseEntity<List<YouthResponseDto>> findYouths(
            @RequestParam(name = "nome", defaultValue = "", required = false) String name
    ){
        List<Youth> youthList = name.isBlank() ? youthService.findAll() : youthService.findByName(name);

        return ResponseEntity.ok(youthMapper.toResponseDtoList(youthList));
    }

    @GetMapping("/resumo")
    public ResponseEntity<List<YouthSimpleDto>> findYouthsSummary(
        @RequestParam(name = "nome", defaultValue = "", required = false) String name
    ){
        List<Youth> youthList = name.isBlank() ? youthService.findAll() : youthService.findByName(name);
        return ResponseEntity.ok(youthMapper.toSimpleResponseDtoList(youthList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<YouthResponseDto> findYouthById(
        @PathVariable("id")
        @Positive Long id
    ){
        Youth response = youthService.findById(id);

        YouthResponseDto youthResponseDto = youthMapper.toResponseDto(response);
        return ResponseEntity.ok(youthResponseDto);
    }

    @PostMapping
    public ResponseEntity<Void> createYouth(@RequestBody @Valid YouthCreateDto youthCreateDto){
        Youth youthToSave = youthMapper.toEntity(youthCreateDto);
        Youth savedYouth = youthService.save(youthToSave);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedYouth.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<YouthResponseDto> updateYouth(@PathVariable Long id, @RequestBody YouthUpdateDto updatedDataDto){
        Youth youthResponse = youthService.update(id, updatedDataDto);
        YouthResponseDto youthResponseDto = youthMapper.toResponseDto(youthResponse);
        return ResponseEntity.ok(youthResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteYouthById(@PathVariable Long id){
        youthService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
