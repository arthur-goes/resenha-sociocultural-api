package br.com.resenhasociocultural.apiresenha.service;

import br.com.resenhasociocultural.apiresenha.dto.YouthUpdateAdminDto;
import br.com.resenhasociocultural.apiresenha.mapper.YouthMapper;
import br.com.resenhasociocultural.apiresenha.model.Youth;
import br.com.resenhasociocultural.apiresenha.repository.YouthRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class YouthService {

    private final YouthRepository youthRepository;
    private final YouthMapper youthMapper;

    public YouthService(YouthRepository youthRepository, YouthMapper youthMapper) {
        this.youthRepository = youthRepository;
        this.youthMapper = youthMapper;
    }

    public List<Youth> findAll(){
        return youthRepository.findAll();
    }

    public Youth save(Youth youth){
        return youthRepository.save(youth);
    }

    public Youth update(YouthUpdateAdminDto youthUpdateAdminDto){
        Optional<Youth> youth = youthRepository.findById(youthUpdateAdminDto.id());
        youthMapper.updateYouthFromDto(youthUpdateAdminDto, youth.get());
        return youthRepository.save(youth);
    }
}
