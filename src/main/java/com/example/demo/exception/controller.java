package com.example.demo.exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
public class controller {
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public void testE() throws R {


        try {
            int i = 10 / 0;
        } catch (Exception e) {
            throw new R(e.getMessage());
        }


    }
}
