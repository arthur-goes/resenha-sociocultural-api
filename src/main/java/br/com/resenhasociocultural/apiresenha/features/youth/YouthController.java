package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultura.apiresenha.api.controller.YouthsApi;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/youths")
public class YouthController implements YouthsApi {

    private final YouthService youthService;
    private final YouthMapper youthMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('COORDINATOR', 'ADMIN')")
    public ResponseEntity<List<YouthResponse>> findYouths(
            @RequestParam(name = "name", defaultValue = "", required = false) String name
    ){
        List<Youth> youthList = name.isBlank() ? youthService.findAll() : youthService.findByName(name);

        return ResponseEntity.ok(youthMapper.toResponseDtoList(youthList));
    }

    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('USER', 'COORDINATOR', 'ADMIN')")
    public ResponseEntity<List<YouthSummary>> findYouthsSummarized(
        @RequestParam(name = "name", defaultValue = "", required = false) String name
    ){
        List<Youth> youthList = name.isBlank() ? youthService.findAll() : youthService.findByName(name);
        return ResponseEntity.ok(youthMapper.toSimpleResponseDtoList(youthList));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDINATOR', 'ADMIN')")
    public ResponseEntity<YouthResponse> findYouthById(
        @PathVariable("id")
        @Positive
        Long id
    ){
        Youth response = youthService.findById(id);

        YouthResponse youthResponse = youthMapper.toResponseDto(response);
        return ResponseEntity.ok(youthResponse);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('COORDINATOR', 'ADMIN')")
    public ResponseEntity<Void> createYouth(@RequestBody @Valid YouthCreate youthCreate){
        Youth youthToSave = youthMapper.toEntity(youthCreate);
        Youth savedYouth = youthService.save(youthToSave);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedYouth.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDINATOR', 'ADMIN')")
    public ResponseEntity<YouthResponse> updateYouth(@PathVariable Long id, @RequestBody YouthUpdate updatedData){
        Youth youthResponse = youthService.update(id, updatedData);
        YouthResponse youthResponseDto = youthMapper.toResponseDto(youthResponse);
        return ResponseEntity.ok(youthResponseDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteYouthById(@PathVariable Long id){
        youthService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}