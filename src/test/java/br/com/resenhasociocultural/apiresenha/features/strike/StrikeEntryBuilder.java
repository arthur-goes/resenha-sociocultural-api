package br.com.resenhasociocultural.apiresenha.features.strike;

import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StrikeEntryBuilder {
    private Long id = 1L;
    private Youth youth = null;
    private int amount;
    private String reason;
    private Meeting meeting = null;
    private boolean active;

    public StrikeEntryBuilder aStrike(){
        return new StrikeEntryBuilder();
    }

    public StrikeEntryBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public StrikeEntryBuilder withoutId(){
        this.id = null;
        return this;
    }

    public StrikeEntryBuilder amount(int amount){
        this.amount = amount;
        return this;
    }

    public StrikeEntryBuilder withReason(String reason){
        this.reason = reason;
        return this;
    }

    public StrikeEntryBuilder active(){
        this.active = true;
        return this;
    }

    public StrikeEntryBuilder inactive(){
        this.active = false;
        return this;
    }

    public StrikeEntry build(){
        return new StrikeEntry(
            id,
            youth,
            amount,
            reason,
            meeting,
            active
        );
    }
}
