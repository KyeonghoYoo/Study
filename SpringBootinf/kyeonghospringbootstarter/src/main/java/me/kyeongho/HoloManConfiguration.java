package me.kyeongho;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HoloManConfiguration {

    @Bean
    public HoloMan holoMan() {
        HoloMan holoMan = new HoloMan();
        holoMan.setName("Kyeongho");
        holoMan.setHowLong(30);

        return holoMan;
    }
}
