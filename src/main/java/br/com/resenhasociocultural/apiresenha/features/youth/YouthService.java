package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthUpdateDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@AllArgsConstructor
@Service
public class YouthService {

    private final YouthRepository youthRepository;
    private final YouthMapper youthMapper;
    private final YouthSpecs youthSpecs;

    public Youth findById(Long id){
        return youthRepository.findById(id)
            .orElseThrow(resourceNotFoundExceptionById(id));
    }

    public List<Youth> findByName(String name){
        return youthRepository.findAll(youthSpecs.nameOrSurnameLike(name));
    }

    public List<Youth> findAll(){
        return youthRepository.findAll();
    }

    public Youth save(Youth youth){
        return youthRepository.save(youth);
    }

    public Youth update(YouthUpdateDto youthUpdatedDataDto){
        Youth youth = youthRepository.findById(youthUpdatedDataDto.id())
                .orElseThrow(resourceNotFoundExceptionById(youthUpdatedDataDto.id()));
        youthMapper.updateYouthFromDto(youthUpdatedDataDto, youth);
        return youthRepository.save(youth);
    }

    public void deleteById(Long id){
        Youth youth = findById(id);
        youthRepository.delete(youth);
    }

    public Supplier<ResourceNotFoundException> resourceNotFoundExceptionById(Long id){
        return () -> new ResourceNotFoundException("Não foi possível encontrar um cadastro para o jovem de id " + id);
    }

    public List<Youth> findAllActiveYouths() {
        return youthRepository.findByActive(true);
    }
}
