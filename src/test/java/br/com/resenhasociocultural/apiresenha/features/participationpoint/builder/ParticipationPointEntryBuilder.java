package br.com.resenhasociocultural.apiresenha.features.participationpoint.builder;

import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPointEntry;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParticipationPointEntryBuilder {
    private Long id;
    private Youth youth = null;
    private int amount;
    private String reason;
    private Meeting meeting = null;
    private boolean active = true;

    public static ParticipationPointEntryBuilder aParticipationPointEntry(){
        return new ParticipationPointEntryBuilder();
    }

    public ParticipationPointEntryBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public ParticipationPointEntryBuilder withoutId(){
        this.id = null;
        return this;
    }

    public ParticipationPointEntryBuilder withYouth(Youth youth){
        this.youth = youth;
        return this;
    }

    public ParticipationPointEntryBuilder withAmount(int amount){
        this.amount = amount;
        return this;
    }

    public ParticipationPointEntryBuilder withReason(String reason){
        this.reason = reason;
        return this;
    }

    public ParticipationPointEntryBuilder active(){
        this.active = true;
        return this;
    }

    public ParticipationPointEntryBuilder inactive(){
        this.active = false;
        return this;
    }

    public ParticipationPointEntry build(){
        return new ParticipationPointEntry(
            id,
            youth,
            amount,
            reason,
            meeting,
            active
        );
    }
}
