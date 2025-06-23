package br.com.resenhasociocultural.apiresenha.mapper;

import br.com.resenhasociocultural.apiresenha.dto.strike.StrikeCreateDto;
import br.com.resenhasociocultural.apiresenha.model.Strike;

public interface StrikeMapper {
    Strike toEntity(StrikeCreateDto dto);
}
