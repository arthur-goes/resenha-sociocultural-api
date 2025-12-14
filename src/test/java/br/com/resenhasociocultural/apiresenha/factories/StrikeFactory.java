package br.com.resenhasociocultural.apiresenha.factories;

import br.com.resenhasociocultural.apiresenha.factories.dto.StrikeFactoryResult;
import br.com.resenhasociocultural.apiresenha.features.strike.Strike;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static br.com.resenhasociocultural.apiresenha.features.strike.builder.StrikeBuilder.aStrike;

public class StrikeFactory {

    public static StrikeFactoryResult generateStrikes(List<Youth> youths){
        var strikes = generateDefaultStrikes(youths);
        return new StrikeFactoryResult(
            strikes,
            new ArrayList<>(),
            new ArrayList<>()
        );
    }
    private static List<Strike> generateDefaultStrikes(List<Youth> youths){
        return youths.stream().map(youth -> aStrike()
            .withoutId()
            .withYouth(youth)
            .withAmount(1)
            .withReason("Some Reason")
            .active()
            .build()).collect(Collectors.toList());
    }

    public static StrikeFactoryResult randomlyGenerateStrikesWithSameYouth(List<Youth> youths, int youthsWithMultipleStrikesAmount){
        if (youthsWithMultipleStrikesAmount >= youths.size()){
            throw new IllegalArgumentException("Youths with multiple strikes amount can not be greater than the total number of youths");
        }
        List<Strike> strikes = generateDefaultStrikes(youths);
        List<Youth> youthsWithMoreThanOneStrike = new ArrayList<>();
        List<List<Strike>> strikePairsWithSameYouth = new ArrayList<>();

        var shuffledIndexes = getShuffledIndexes(youths.size());
        List<Integer> reincidentIndexes = shuffledIndexes.subList(0, youthsWithMultipleStrikesAmount);
        reincidentIndexes.forEach(index -> {
            var youth = strikes.get(index).getYouth();

            var strike = aStrike()
                .withoutId()
                .withYouth(youth)
                .withAmount(1)
                .withReason("Another Reason")
                .active()
                .build();

            strikes.add(strike);
            youthsWithMoreThanOneStrike.add(youth);
            strikePairsWithSameYouth.add(List.of(strikes.get(index), strike));
        });

        return new StrikeFactoryResult(
            strikes,
            strikePairsWithSameYouth,
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
