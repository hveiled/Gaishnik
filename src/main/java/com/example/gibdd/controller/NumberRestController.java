package com.example.gibdd.controller;

import com.example.gibdd.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NumberRestController {

    @Autowired
    NumberService numberService;

    @RequestMapping(method = RequestMethod.GET, path = "/random")
    public String random() {
        return numberService.getRandomNumber();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/next")
    public String next() {
        return numberService.getNextNumber();
    }
}
