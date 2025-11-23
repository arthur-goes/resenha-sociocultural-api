package br.com.resenhasociocultural.apiresenha.features.participationpoint;

import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

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
