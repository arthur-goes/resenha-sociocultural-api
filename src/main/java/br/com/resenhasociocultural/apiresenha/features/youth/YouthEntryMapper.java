package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthEntryDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;

public interface YouthEntryMapper<Entry extends YouthEntry, EntryDto extends YouthEntryDto> {
    Entry toEntity(EntryDto entryDto, @Context YouthService youthService);

    @AfterMapping
    default void linkYouth(EntryDto entryDto, @MappingTarget Entry meetingChildCollection, @Context YouthService youthService){
        Long youthId = entryDto.youthId();
        Youth youth = youthService.findById(youthId);
        meetingChildCollection.setYouth(youth);
    };
}
