package br.com.resenhasociocultural.apiresenha.factories.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.Youth;

import java.util.ArrayList;

public record YouthFactoryResult(
    ArrayList<Youth> generatedYouths,
    ArrayList<Youth> activeYouths,
    ArrayList<Youth> inactiveYouths
) {
}
