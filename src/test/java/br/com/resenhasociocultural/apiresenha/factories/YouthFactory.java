package br.com.resenhasociocultural.apiresenha.factories;

import br.com.resenhasociocultural.apiresenha.factories.dto.YouthFactoryResult;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.builder.YouthBuilder;
import br.com.resenhasociocultural.apiresenha.utils.CPFGenerator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import static br.com.resenhasociocultural.apiresenha.features.youth.builder.YouthBuilder.aYouth;

public class YouthFactory {

    private static final int YOUTH_MINIMUM_AGE = 12;
    private static final int YOUTH_MAX_AGE = 18;
    private static final Random RANDOM = new Random();

    public static YouthFactoryResult generateYouthsWithIds(int amount, int inactivePercentage){
        return generateYouths(amount, true, inactivePercentage);
    }

    public static YouthFactoryResult generateYouthsWithIds(int amount){
        return generateYouths(amount, true, 0);
    }

    public static YouthFactoryResult generateYouthsWithoutIds(int amount, int inactivePercentage){
        return generateYouths(amount, false, inactivePercentage);
    }

    public static YouthFactoryResult generateYouthsWithoutIds(int amount){
        return generateYouths(amount, false, 0);
    }

    private static YouthFactoryResult generateYouths(int amount, boolean generateIds, int inactivePercentage){
        ArrayList<Youth> youths = new ArrayList<>();
        ArrayList<Youth> activeYouths = new ArrayList<>();
        ArrayList<Youth> inactiveYouths = new ArrayList<>();

        ArrayList<Integer> inactiveIndexes = generateInactiveIndexes(amount, inactivePercentage);

        for (int i = 1; i <= amount; i++){
            Youth buildedYouth;
            String cpf = CPFGenerator.generate();

            YouthBuilder youth = aYouth().withoutId().active();

            if (generateIds) {
                youth = youth.withId((long) i);
            }

            youth = youth
                .withFirstName("FisrtName " + i)
                .withSurname("Surname " + i)
                .withCpf(cpf)
                .withBirthDate(getRandomBirthDate())
                .withMotherName("Mother " + i)
                .withFatherName("Father " + i);

            if (inactiveIndexes.contains(i)) {
                buildedYouth  = youth.inactive().build();
                inactiveYouths.add(buildedYouth);
            } else {
                buildedYouth = youth.active().build();
                activeYouths.add(buildedYouth);
            }

            youths.add(buildedYouth);
        }

        return new YouthFactoryResult(
            youths,
            activeYouths,
            inactiveYouths
        );
    }

    private static ArrayList<Integer> generateInactiveIndexes(int amount, int inactivePercentage){
        int inactiveQuantity = Math.round(amount * inactivePercentage * 0.01f);
        ArrayList<Integer> inactiveIndexes = new ArrayList<>();

        if (inactiveQuantity > 0) {
            inactiveIndexes.addAll(
                RANDOM
                    .ints(inactiveQuantity, 1, amount + 1)
                    .distinct()
                    .boxed()
                    .collect(Collectors.toCollection(ArrayList::new))
            );
        }

        return inactiveIndexes;
    }

    private static LocalDate getRandomBirthDate(){
        double randomValue = RANDOM.nextDouble();
        var now = LocalDate.now();

        var biggestBirthDate = now.minusYears(YOUTH_MINIMUM_AGE);
        var lowestBirthDate = now.minusYears(YOUTH_MAX_AGE);
        var daysRange = ChronoUnit.DAYS.between(lowestBirthDate, biggestBirthDate);

        int daysToAdd = (int) (randomValue * daysRange);

        return lowestBirthDate.plusDays(daysToAdd);

    }
}
