package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthEntryDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface YouthEntryMapper<Entry extends YouthEntry, EntryDto extends YouthEntryDto> {

    Entry toEntity(EntryDto entryDto);

    @AfterMapping
    default void linkYouth(EntryDto entryDto, @MappingTarget Entry youthEntry){
        Youth youth = new Youth();
        youth.setId(entryDto.youthId());
        youth.setFirstName(entryDto.youthFirstName());
        youth.setSurname(entryDto.youthSurname());

        youthEntry.setYouth(youth);
    };
}
