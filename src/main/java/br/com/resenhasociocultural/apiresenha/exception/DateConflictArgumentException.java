package br.com.resenhasociocultural.apiresenha.exception;

public class DateConflictArgumentException extends IllegalArgumentException{
    private static String MESSAGE = "Inconsistência nos parâmetros de data enviado. A filtragem por data deve ser para uma data específica ou um intervalo, não para os dois simultâneamente.";
    public DateConflictArgumentException(){
        super(MESSAGE);
    }
}
