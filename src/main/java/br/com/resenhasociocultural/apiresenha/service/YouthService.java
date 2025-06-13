package br.com.resenhasociocultural.apiresenha.service;

import br.com.resenhasociocultural.apiresenha.dto.YouthUpdateAdminDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.mapper.YouthMapper;
import br.com.resenhasociocultural.apiresenha.model.Youth;
import br.com.resenhasociocultural.apiresenha.repository.YouthRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class YouthService {

    private final YouthRepository youthRepository;
    private final YouthMapper youthMapper;

    public YouthService(YouthRepository youthRepository, YouthMapper youthMapper) {
        this.youthRepository = youthRepository;
        this.youthMapper = youthMapper;
    }

    public Youth findYouthById(Long id){
        return youthRepository.findById(id)
            .orElseThrow(resourceNotFoundExceptionById(id));
    }

    public List<Youth> findByName(String name){
        return youthRepository.findByFirstNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(name, name);
    }

    public List<Youth> findAll(){ return youthRepository.findAll();}

    public Youth save(Youth youth){ return youthRepository.save(youth);}

    public Youth update(YouthUpdateAdminDto youthUpdatedDataDto){
        Youth youth = youthRepository.findById(youthUpdatedDataDto.id())
                .orElseThrow(resourceNotFoundExceptionById(youthUpdatedDataDto.id()));
        youthMapper.updateYouthFromDto(youthUpdatedDataDto, youth);
        return youthRepository.save(youth);
    }

    public void deleteById(Long id){
        Youth youth = youthRepository.findById(id).orElseThrow(resourceNotFoundExceptionById(id));
        youthRepository.deleteById(id);
    }

    public Supplier<ResourceNotFoundException> resourceNotFoundExceptionById(Long id){
        return () -> new ResourceNotFoundException("Não foi possível encontrar um cadastro para o jovem de id " + id);
    }

}
