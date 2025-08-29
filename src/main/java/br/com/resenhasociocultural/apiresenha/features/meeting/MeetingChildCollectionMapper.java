package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;

public interface MeetingChildCollectionMapper<Child extends MeetingChildCollection, Dto extends MeetingChildCollectionDto> {
    Child toEntity(Dto dto, @Context YouthService youthService);

    @AfterMapping
    default void linkYouth(Dto dto, @MappingTarget Child meetingChildCollection, @Context YouthService youthService){
        Long youthId = dto.youthId();
        Youth youth = youthService.findById(youthId);
        meetingChildCollection.setYouth(youth);
    };
}
