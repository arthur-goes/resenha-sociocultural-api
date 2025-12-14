package br.com.resenhasociocultural.apiresenha.features.participationpoint.builder;

import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParticipationPointBuilder {
    private Long id;
    private Youth youth = null;
    private int amount;
    private String reason;
    private Meeting meeting = null;
    private boolean active = true;

    public static ParticipationPointBuilder aParticipationPoint(){
        return new ParticipationPointBuilder();
    }

    public ParticipationPointBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public ParticipationPointBuilder withoutId(){
        this.id = null;
        return this;
    }

    public ParticipationPointBuilder withYouth(Youth youth){
        this.youth = youth;
        return this;
    }

    public ParticipationPointBuilder withAmount(int amount){
        this.amount = amount;
        return this;
    }

    public ParticipationPointBuilder withReason(String reason){
        this.reason = reason;
        return this;
    }

    public ParticipationPointBuilder active(){
        this.active = true;
        return this;
    }

    public ParticipationPointBuilder inactive(){
        this.active = false;
        return this;
    }

    public ParticipationPoint build(){
        return new ParticipationPoint(
            id,
            youth,
            amount,
            reason,
            meeting,
            active
        );
    }
}
