package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 */
@RestController
public class SimpleController {

    private final Logger logger = LoggerFactory.getLogger(SimpleController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> sayHello() {
        logger.info("system 2 gets hello");
        return new ResponseEntity<>("system 2 say hello", HttpStatus.OK);
    }

    @RequestMapping(value = "/answer",method = RequestMethod.GET)
    public ResponseEntity<String>ifFirstSayOk(@RequestParam("value")String value){
        return new ResponseEntity<>("you are right I have "+value+" value identity",HttpStatus.OK);
    }
}
