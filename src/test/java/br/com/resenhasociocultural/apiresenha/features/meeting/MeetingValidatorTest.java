package br.com.resenhasociocultural.apiresenha.features.meeting;


import br.com.resenhasociocultural.apiresenha.factories.dto.MeetingFactoryResult;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static br.com.resenhasociocultural.apiresenha.factories.MeetingFactory.generateCompleteMeeting;
import static br.com.resenhasociocultural.apiresenha.factories.YouthFactory.generateYouthsWithIds;

public class MeetingValidatorTest {

    @InjectMocks
    MeetingValidator meetingValidator;

    @Mock
    YouthService youthService;

    @Test
    public void givenInvalidYouthNameInEntry_shouldThrowException(){
        List<Youth> entryYouths = generateYouthsWithIds(10).generatedYouths();
        Meeting meeting = generateCompleteMeeting(entryYouths).meeting();
        List<Youth> mockedYouths = generateYouthsWithIds(10).generatedYouths();
        mockedYouths.get(0).setFirstName("alteredName");

    }
}
