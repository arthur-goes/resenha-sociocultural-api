package br.com.resenhasociocultural.apiresenha.exception;

public class IllegalDateFilterArgumentException extends IllegalArgumentException{
    private static String DATE_CONFLICT_MESSAGE = "Inconsistência nos parâmetros de data enviado. A filtragem por data deve ser para uma data específica ou um intervalo, não para os dois simultâneamente.";
    private static String INCONSISTENT_DATE_INTERVAL_MESSAGE = "Inconsistência nos parâmetros de data enviado. A filtragem por intervalo de data deve obrigatoriamente ter uma data inicial e uma data final.";

    public IllegalDateFilterArgumentException(String message){
        super(message);
    }
}
