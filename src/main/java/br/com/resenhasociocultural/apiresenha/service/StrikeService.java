package br.com.resenhasociocultural.apiresenha.service;

import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.model.Strike;
import br.com.resenhasociocultural.apiresenha.repository.StrikeRepository;
import org.springframework.stereotype.Service;

@Service
public class StrikeService {
    private StrikeRepository strikeRepository;
    private YouthService youthService;

    public StrikeService(StrikeRepository strikeRepository, YouthService youthService) {
        this.strikeRepository = strikeRepository;
        this.youthService = youthService;
    }

    public Strike findById(Long id){
        return strikeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível encotrar um strike com id " + id));
    }
}
