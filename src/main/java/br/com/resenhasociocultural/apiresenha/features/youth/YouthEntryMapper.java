package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthEntryDto;
import org.mapstruct.*;

public interface YouthEntryMapper<Entry extends YouthEntry, EntryDto extends YouthEntryDto> {

    Entry toEntity(EntryDto entryDto);

    EntryDto toCreateDto(Entry entry);

}
