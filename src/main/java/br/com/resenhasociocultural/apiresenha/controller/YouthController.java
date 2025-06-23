package br.com.resenhasociocultural.apiresenha.controller;

import br.com.resenhasociocultural.apiresenha.dto.youth.*;
import br.com.resenhasociocultural.apiresenha.enums.YouthViewType;
import br.com.resenhasociocultural.apiresenha.mapper.YouthMapper;
import br.com.resenhasociocultural.apiresenha.model.Youth;
import br.com.resenhasociocultural.apiresenha.service.YouthService;
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
    public ResponseEntity<List<? extends YouthView>> findYouth(
            @RequestParam(name = "view", defaultValue = "COMPLETE", required = false) YouthViewType view,
            @RequestParam(name = "nome", defaultValue = "", required = false) String name
    ){
        List<Youth> youthList = name.isBlank() ? youthService.findAll() : youthService.findByName(name);
        if (view == YouthViewType.SIMPLE){
            List<YouthSimpleDto> simpleList = youthList.stream().map(youthMapper::youthToSimpleResponseDTO).toList();
            return ResponseEntity.ok(simpleList);
        } else {
            List<YouthResponseDto> completeList = youthList.stream().map(youthMapper::youthToResponseDTO).toList();
            return ResponseEntity.ok(completeList);
        }
        //Implementar validação customizada, para retornar uma mensagem mais amigável para o cliente no caso de um view inválido.
    }

    @GetMapping("{id}")
    public ResponseEntity<YouthResponseDto> findYouthById(@PathVariable("id") Long id){
        Youth youth = youthService.findById(id);
        YouthResponseDto youthResponseDto = youthMapper.youthToResponseDTO(youth);
        return ResponseEntity.ok(youthResponseDto);
    }

    @PostMapping
    public ResponseEntity<YouthResponseDto> addYouth(@RequestBody YouthCreateDto youthCreateDto){
        Youth youthToSave = youthMapper.youthCreateDtoToEntity(youthCreateDto);
        Youth savedYouth = youthService.save(youthToSave);

        YouthResponseDto youthResponseDto = youthMapper.youthToResponseDTO(savedYouth);
        return ResponseEntity.status(HttpStatus.CREATED).body(youthResponseDto);
    }

    @PatchMapping("{id}/useradmin")
    public ResponseEntity<YouthResponseDto> updateYouthByAdmin(@RequestBody YouthUpdateAdminDto youthUpdatedDataDto){
        Youth youthResponse = youthService.update(youthUpdatedDataDto);
        YouthResponseDto youthResponseDto = youthMapper.youthToResponseDTO(youthResponse);
        return ResponseEntity.ok(youthResponseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteYouthById(@PathVariable Long id){
        youthService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
