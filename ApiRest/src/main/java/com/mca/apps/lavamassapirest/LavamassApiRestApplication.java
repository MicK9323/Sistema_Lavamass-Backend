package com.mca.apps.lavamassapirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        LavamassApiRestApplication.class,
        Jsr310Converters.class
})
public class LavamassApiRestApplication {

    @PostConstruct
    void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
    }

    public static void main(String[] args) {
        SpringApplication.run(LavamassApiRestApplication.class, args);
    }
}
