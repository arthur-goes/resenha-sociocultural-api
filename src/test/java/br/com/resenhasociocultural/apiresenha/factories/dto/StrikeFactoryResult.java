package br.com.resenhasociocultural.apiresenha.factories.dto;

import br.com.resenhasociocultural.apiresenha.features.strike.Strike;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;

import java.util.List;

public record StrikeFactoryResult(
   List<Strike> generatedStrikes,
   List<List<Strike>> strikePairsWithSameYouth,
   List<Youth> youthsWithMoreThanOneStrike

) {}
