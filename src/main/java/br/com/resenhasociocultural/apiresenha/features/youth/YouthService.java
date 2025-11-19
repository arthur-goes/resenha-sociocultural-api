package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthUpdateDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

@AllArgsConstructor
@Service
public class YouthService {

    private final YouthRepository youthRepository;
    private final YouthMapper youthMapper;
    private final YouthNameSpecs youthNameSpecs;

    public Youth findById(Long id){
        return youthRepository.findById(id).orElseThrow(resourceNotFoundSupplier(id));
    }

    public List<Youth> findByName(String name){
        return youthRepository.findAll(youthNameSpecs.nameOrSurnameLikeForYouth(name));
    }

    public List<Youth> findAll(){
        return youthRepository.findAll();
    }

    public Youth save(Youth youth){
        return youthRepository.save(youth);
    }

    public Youth update(Long id, YouthUpdateDto youthUpdatedDataDto){
        Youth youth = youthRepository.findById(id)
                .orElseThrow(resourceNotFoundSupplier(id));
        youthMapper.updateYouthFromDto(youthUpdatedDataDto, youth);
        return youthRepository.save(youth);
    }

    public void deleteById(Long id){
        Youth youth = findById(id);
        youthRepository.delete(youth);
    }

    public Supplier<ResourceNotFoundException> resourceNotFoundSupplier(Long id){
        return () -> new ResourceNotFoundException("Não foi possível encontrar um cadastro válido de um jovem para o id " + id);
    }

    public List<Youth> findAllActiveYouths() {
        return youthRepository.findByActive(true);
    }

    public Set<Long> findValidYouthIdsIn(Set<Long> idList){
        return youthRepository.findIdsByIdIn(idList);
    };

    public Youth getYouthReference(Long id){
        return youthRepository.getReferenceById(id);
    }
}
