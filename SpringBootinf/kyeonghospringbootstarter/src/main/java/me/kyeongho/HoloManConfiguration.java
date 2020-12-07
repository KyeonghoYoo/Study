package me.kyeongho;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HoloManConfiguration.class)
@ConfigurationProperties(prefix = "holoman")
public class HoloManConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public HoloMan holoMan(HolomanProperties properties) {
//        HoloMan holoMan = new HoloMan();
//        holoMan.setName("Kyeongho");
//        holoMan.setHowLong(30);
    	HoloMan holoMan = new HoloMan();
    	holoMan.setName(properties.getName());
    	holoMan.setHowLong(properties.getHowLong());

        return holoMan;
    }
}
