package br.com.resenhasociocultural.apiresenha.features.strike;

import br.com.resenhasociocultural.apiresenha.features.meeting.MeetingChildCollectionMapper;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeCreateDto;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import org.mapstruct.Mapper;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public interface StrikeMapper extends MeetingChildCollectionMapper<Strike, StrikeCreateDto> {
    Set<StrikeResponseDto> toResponseDto(Set<Strike> strikes);

}
