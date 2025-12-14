package br.com.resenhasociocultural.apiresenha.exception;

public class InconsistentDateIntervalArgumentException extends IllegalArgumentException{
    private static String MESSAGE = "Inconsistência nos parâmetros de data enviado. A filtragem por intervalo de data deve obrigatoriamente ter uma data inicial e uma data final.";

    public InconsistentDateIntervalArgumentException(){
        super(MESSAGE);
    }
}
