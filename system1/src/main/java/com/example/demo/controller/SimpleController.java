package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * .
 */
@RestController
@RequestMapping(value = "/")
public class SimpleController {

    private final Logger logger = LoggerFactory.getLogger(SimpleController.class);

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ResponseEntity<String> sayHello() {
        logger.info("system 1 gets hello");
        return new ResponseEntity<>("system 1 say hello", HttpStatus.OK);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isPresent(@RequestParam("value") String value) {
        logger.info("enter in controller");
        if (value.equalsIgnoreCase("first")) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
}
