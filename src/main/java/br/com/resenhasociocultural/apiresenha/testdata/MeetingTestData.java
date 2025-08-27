package br.com.resenhasociocultural.apiresenha.testdata;

import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MeetingTestData {

    public static List<Meeting> getData(){
        List<Meeting> meetingList = new ArrayList<>();

        meetingList.add(
          new Meeting()
        );
        return meetingList;
    }


}
