package br.com.resenhasociocultural.apiresenha.features.strike;

import br.com.resenhasociocultural.apiresenha.features.youth.YouthEntryMapper;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeCreate;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeResponse;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public interface StrikeMapper extends YouthEntryMapper<Strike, StrikeCreate> {
    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "meeting.date", target = "date")
    StrikeResponse toResponseDto(Strike strike);

    Set<StrikeResponse> toResponseDtoList(Set<Strike> strikes);
    List<StrikeResponse> toResponseDtoList(List<Strike> strikes);

}
