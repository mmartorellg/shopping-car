package com.shoppingcar.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class HealthWorldController {

    @RequestMapping("/hello")
    public String helloWorld(@RequestParam(value = "name", defaultValue = "World") String name) {

        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        return "Hello " + name + "!!";
    }
}
