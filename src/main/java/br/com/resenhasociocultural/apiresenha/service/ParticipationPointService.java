package br.com.resenhasociocultural.apiresenha.service;

import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.model.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.repository.ParticipationPointRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ParticipationPointService {

    private ParticipationPointRepository participationPointRepository;

    public ParticipationPointService(ParticipationPointRepository participationPointRepository) {
        this.participationPointRepository = participationPointRepository;
    }

    public ParticipationPoint findById(Long id){
        return participationPointRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Não foi possível encontrar um lançamento de ponto de participação com id " + id)
        );
    }
}
