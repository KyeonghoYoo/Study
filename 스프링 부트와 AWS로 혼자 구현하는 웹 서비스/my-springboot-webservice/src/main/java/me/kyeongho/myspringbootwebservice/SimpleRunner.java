package me.kyeongho.myspringbootwebservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class SimpleRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        int i = 0;

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        for (Integer integer : list) {
            log.info("print = " + integer);
        }

        log.info("print i = " + i);

        String secretKye = "c29tZS1zZWNyZXQta2V5";

        log.info("secretKye = " + secretKye);

    }
}
