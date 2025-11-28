package br.com.resenhasociocultural.apiresenha.features.strike.builder;

import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.strike.Strike;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StrikeBuilder {
    private Long id = 1L;
    private Youth youth = null;
    private int amount;
    private String reason;
    private Meeting meeting = null;
    private boolean active;

    public static StrikeBuilder aStrike(){
        return new StrikeBuilder();
    }

    public StrikeBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public StrikeBuilder withoutId(){
        this.id = null;
        return this;
    }

    public StrikeBuilder withYouth(Youth youth){
        this.youth = youth;
        return this;
    }

    public StrikeBuilder withAmount(int amount){
        this.amount = amount;
        return this;
    }

    public StrikeBuilder withReason(String reason){
        this.reason = reason;
        return this;
    }

    public StrikeBuilder active(){
        this.active = true;
        return this;
    }

    public StrikeBuilder inactive(){
        this.active = false;
        return this;
    }

    public Strike build(){
        return new Strike(
            id,
            youth,
            amount,
            reason,
            meeting,
            active
        );
    }
}
