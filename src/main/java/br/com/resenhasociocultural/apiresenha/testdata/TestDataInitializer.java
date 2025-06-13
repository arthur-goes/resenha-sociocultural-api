package br.com.resenhasociocultural.apiresenha.testdata;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class TestDataInitializer {

    private final YouthTestData youthTestData;

    TestDataInitializer(YouthTestData youthTestData){
        this.youthTestData = youthTestData;
    }

    public void initialize(){
        youthTestData.createYouthTestData();
    }
}
