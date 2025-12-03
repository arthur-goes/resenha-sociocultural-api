package br.com.resenhasociocultural.apiresenha.factories.dto;

import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;

import java.util.List;

public record ParticipationPointFactoryResult(
    List<ParticipationPoint> generatedParticipationPoints,
    List<List<ParticipationPoint>> participationPointPairsWithSameYouth,
    List<Youth> youthsWithMoreThanOneStrike
) {
}
