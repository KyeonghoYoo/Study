package me.kyeongho.secondservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/second-service/")
public class SecondServiceController {

    Environment env;

    @Autowired
    public SecondServiceController(Environment env) {
        this.env = env;
    }

    @GetMapping("welcome")
    public String welcome() {
        return "welcome to the Second Service";
    }

    @GetMapping("message")
    public String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return "Hello World in Second Service.";
    }

    @GetMapping("check")
    public String check(HttpServletRequest request) {
        log.info("Sever port = {}", request.getServerPort());
        return String.format("Hi, there. This is a message from Second Service on PORT %s",
                env.getProperty("local.server.port"));
    }
}