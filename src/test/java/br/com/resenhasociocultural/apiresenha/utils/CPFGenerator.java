package br.com.resenhasociocultural.apiresenha.utils;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class CPFGenerator {

    private static final int BASE_DIGITS_COUNT = 9;
    private static final int TOAL_DIGITS_COUNT = 11;
    private static final int FIRST_VERIFICATION_DIGIT_MULTIPLIER = 10;
    private static final int SECOND_VERIFICATION_DIGIT_MULTIPLIER = 11;

    private CPFGenerator(){
    }

    public static String generate(){
        return generate(false);
    }

    public static String generateFormatted(){
        return generate(true);
    }

    private static String generate(boolean formatted){
        var digits = generateNormalDigits();
        generateVerificationDigits(digits);
        return generateFinalString(digits, formatted);
    }

    private static ArrayList<Integer> generateNormalDigits(){
        return new Random()
            .ints(BASE_DIGITS_COUNT,0,9)
            .boxed()
            .collect(
                Collectors.toCollection(ArrayList::new)
            );
    }

    private static void generateVerificationDigits(ArrayList<Integer> digits){
        calculateVerificationDigit(digits);
        calculateVerificationDigit(digits);
    }



    private static void calculateVerificationDigit(ArrayList<Integer> digits){
        int sum = calculateMultiplicationSum(digits);
        int rest = sum % TOAL_DIGITS_COUNT;

        int digit = (rest < 2) ? 0 : (TOAL_DIGITS_COUNT - rest);
        digits.add(digit);

    }

    private static int calculateMultiplicationSum(ArrayList<Integer> digits){
        int sum = 0;
        boolean isCalculatingFirstVerificationDigit = digits.size() == BASE_DIGITS_COUNT;
        int startingMultiplier = isCalculatingFirstVerificationDigit ? FIRST_VERIFICATION_DIGIT_MULTIPLIER : SECOND_VERIFICATION_DIGIT_MULTIPLIER;
        int digitsLastIndex = digits.size() - 1;

        for (int i = 0; i <= digitsLastIndex; i++){
            sum += digits.get(i) * (startingMultiplier - i);
        }
        return sum;
    }

    private static String generateFinalString(ArrayList<Integer> digits, boolean formatted){
        String cpf = digits.stream()
            .map(String::valueOf)
            .collect(Collectors.joining());

        if (formatted){
            return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }

        return cpf;

    }
}
