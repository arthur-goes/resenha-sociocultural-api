package br.com.resenhasociocultural.apiresenha.factories;

import br.com.resenhasociocultural.apiresenha.factories.dto.ParticipationPointFactoryResult;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static br.com.resenhasociocultural.apiresenha.features.participationpoint.builder.ParticipationPointBuilder.aParticipationPoint;

public class ParticipationPointFactory {

    public static ParticipationPointFactoryResult generateParticipationPoints(List<Youth> youths){
        var participationPoints = generateDefaultParticipationPoints(youths);
        return new ParticipationPointFactoryResult(
            participationPoints,
            new ArrayList<>(),
            new ArrayList<>()
        );
    }
    private static List<ParticipationPoint> generateDefaultParticipationPoints(List<Youth> youths){
        return youths.stream().map(youth -> aParticipationPoint()
            .withoutId()
            .withYouth(youth)
            .withAmount(1)
            .withReason("Some Reason")
            .active()
            .build()).collect(Collectors.toList());
    }

    public static ParticipationPointFactoryResult randomlyGenerateParticipationPointsWithSameYouth(List<Youth> youths, int youthsWithMultipleParticipationPointsAmount){
        if (youthsWithMultipleParticipationPointsAmount >= youths.size()){
            throw new IllegalArgumentException("Youths with multiple participation points amount can not be greater than the total number of youths");
        }
        List<ParticipationPoint> participationPoints = generateDefaultParticipationPoints(youths);
        List<Youth> youthsWithMoreThanOneStrike = new ArrayList<>();
        List<List<ParticipationPoint>> participationPointPairsWithSameYouth = new ArrayList<>();

        var shuffledIndexes = getShuffledIndexes(youths.size());
        List<Integer> reincidentIndexes = shuffledIndexes.subList(0, youthsWithMultipleParticipationPointsAmount);
        reincidentIndexes.forEach(index -> {
            var youth = participationPoints.get(index).getYouth();

            var strike = aParticipationPoint()
                .withoutId()
                .withYouth(youth)
                .withAmount(1)
                .withReason("Another Reason")
                .active()
                .build();

            participationPoints.add(strike);
            youthsWithMoreThanOneStrike.add(youth);
            participationPointPairsWithSameYouth.add(List.of(participationPoints.get(index), strike));
        });

        return new ParticipationPointFactoryResult(
            participationPoints,
            participationPointPairsWithSameYouth,
            youthsWithMoreThanOneStrike
        );
    }

    private static List<Integer> getShuffledIndexes(int size) {
        List<Integer> indexes = IntStream.range(0, size)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(indexes);
        return indexes;
    }
}