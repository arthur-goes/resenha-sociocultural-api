package br.com.resenhasociocultural.apiresenha.exception;

public class MalformedMeetingException extends IllegalArgumentException{
    public MalformedMeetingException(String message){
        super(message);
    }
}
