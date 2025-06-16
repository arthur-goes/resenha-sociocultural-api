package br.com.resenhasociocultural.apiresenha.testdata;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class TestDataInitializer {

    private final YouthTestData youthTestData;
    private final AttendanceTestData attendanceTestData;

    TestDataInitializer(YouthTestData youthTestData, AttendanceTestData attendanceTestData){
        this.youthTestData = youthTestData;
        this.attendanceTestData = attendanceTestData;
    }

    public void initialize(){

        youthTestData.createYouthTestData();
        attendanceTestData.createAttendanceTestData();
    }
}
